package ru.mail.track.messenger.socket_messenger.net;

import ru.mail.track.messenger.socket_messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.socket_messenger.chat.ChatStore;
import ru.mail.track.messenger.socket_messenger.chat.ChatStoreStub;
import ru.mail.track.messenger.socket_messenger.commands.Command;
import ru.mail.track.messenger.socket_messenger.commands.CommandHandler;
import ru.mail.track.messenger.socket_messenger.commands.CommandType;
import ru.mail.track.messenger.socket_messenger.commands.command_impl.*;
import ru.mail.track.messenger.socket_messenger.message.MessageStore;
import ru.mail.track.messenger.socket_messenger.message.MessageStoreStub;
import ru.mail.track.messenger.socket_messenger.session.Session;
import ru.mail.track.messenger.socket_messenger.user.UserStore;
import ru.mail.track.messenger.socket_messenger.user.UserStoreStub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 */
public class ThreadedServer {

    //static Logger log = LoggerFactory.getLogger(ThreadedServer.class);

    public static final int PORT = 19000;
    private volatile boolean isRunning;
    private Map<Long, ConnectionHandler> handlers = new HashMap<>();
    private AtomicLong internalCounter = new AtomicLong(0);
    private ServerSocket sSocket;
    private Protocol protocol;
    private SessionManager sessionManager;
    private CommandHandler commandHandler;


    public ThreadedServer(Protocol protocol, SessionManager sessionManager, CommandHandler commandHandler) {
        try {
            this.protocol = protocol;
            this.sessionManager = sessionManager;
            this.commandHandler = commandHandler;
            sSocket = new ServerSocket(PORT);
            sSocket.setReuseAddress(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void startServer() throws Exception {
       // log.info("Started, waiting for connection");

        isRunning = true;
        while (isRunning) {
            Socket socket = sSocket.accept();
           // log.info("Accepted. " + socket.getInetAddress());

            Session session = sessionManager.createSession();
            ConnectionHandler handler = new SocketConnectionHandler(protocol, session, socket);
            session.setConnectionHandler(handler);
            handler.addListener(commandHandler);

            handlers.put(internalCounter.incrementAndGet(), handler);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }

    public void stopServer() {
        isRunning = false;
        for (ConnectionHandler handler : handlers.values()) {
            handler.stop();
        }
    }

    public static void main(String[] args) throws Exception {
        Protocol protocol = new NativeSerializeProtocol();
        SessionManager sessionManager = new SessionManager();

        UserStore userStore = new UserStoreStub();
        ChatStore chatStore = new ChatStoreStub();
        MessageStore messageStore = new MessageStoreStub(chatStore);
        AuthorizationService authorizationService = new AuthorizationService(userStore);

        Map<CommandType, Command> cmds = new HashMap<>();
        cmds.put(CommandType.USER_LOGIN, new LoginCommand(authorizationService, sessionManager));
        cmds.put(CommandType.CHAT_SEND, new SendCommand(chatStore, messageStore, sessionManager));
        cmds.put(CommandType.USER_HELP, new HelpCommand(cmds));
        cmds.put(CommandType.CHAT_CREATE, new ChatCreateCommand(chatStore, userStore));
        cmds.put(CommandType.CHAT_FIND, new ChatFindCommand(chatStore, messageStore));
        cmds.put(CommandType.CHAT_HISTORY, new ChatHistoryCommand(chatStore, messageStore));
        cmds.put(CommandType.CHAT_LIST, new ChatListCommand(chatStore));
        cmds.put(CommandType.NICKNAME_COMMAND, new NicknameCommand());
        cmds.put(CommandType.USER_INFO, new UserInfoCommand(userStore));
        cmds.put(CommandType.USER_PASS, new UserPassCommand(authorizationService));
        CommandHandler handler = new CommandHandler(cmds);


        ThreadedServer server = new ThreadedServer(protocol, sessionManager, handler);

        server.startServer();
    }
}
