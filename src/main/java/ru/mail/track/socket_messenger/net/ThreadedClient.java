package ru.mail.track.socket_messenger.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.*;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.MessageFabric;
import ru.mail.track.socket_messenger.message.message_impl.CommandResultMessage;
import ru.mail.track.socket_messenger.message.message_impl.SendMessage;
import ru.mail.track.socket_messenger.serialization.NativeSerializeProtocol;
import ru.mail.track.socket_messenger.serialization.Protocol;
import ru.mail.track.socket_messenger.session.Session;
import ru.mail.track.socket_messenger.user.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ThreadedClient implements MessageListener {

    static Logger log = LoggerFactory.getLogger(ThreadedClient.class);

    public static final int PORT = 19000;
    public static final String HOST = "localhost";

    ConnectionHandler handler;
    Protocol protocol;

    public ThreadedClient(Protocol protocol) {
        this.protocol = protocol;
        try {
            Socket socket = new Socket(HOST, PORT);
            Session session = new Session();
            handler = new SocketConnectionHandler(protocol, session, socket);

            // Этот класс будет получать уведомления от socket handler
            handler.addListener(this);

            Thread socketHandler = new Thread(handler);
            socketHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
            // exit, failed to open socket
        }
    }

    public void processInput(String line) throws IOException {
        String[] tokens = line.trim().split("\\s+");
        log.info("Tokens: {}", Arrays.toString(tokens));
        Message message = MessageFabric.createMessage(tokens);
        if (message != null) {
            handler.send(message);
        } else {
            System.out.println("Invalid input, check usage: " + line);
        }
    }

    /**
     * Получено сообщение из handler, как обрабатывать
     *
     */

    private void writeOKResult(CommandResult result) {
        switch (result.getCommandType()) {
            case USER_LOGIN:
                System.out.println("You logged as " + ((LoginCommandResult)result).getUser().getLogin());
                break;
            case USER_HELP:
                System.out.println(((HelpCommandResult)result).getUsage());
                break;
            case USER_INFO:
                User user = ((UserInfoCommandResult)result).getUser();
                System.out.println(user.getLogin());
                System.out.println("Id: " + user.getId());
                break;
            case CHAT_LIST:
                System.out.println("Chat list:");
                System.out.println(((ChatListCommandResult)result).getParticipants());
                break;
            case CHAT_CREATE:
                System.out.println("Chat id: " + ((ChatCreateCommandResult)result).getChatId());
                break;
            case CHAT_HISTORY:
                List<Message> messages = ((ChatHistoryCommandResult)result).getMessages();
                for (Message msg : messages) {
                    System.out.println(((SendMessage)msg).getMessage());
                }
                break;
            case CHAT_FIND:
                messages = ((ChatFindCommandResult) result).getMessages();
                for (Message msg : messages) {
                    System.out.println(((SendMessage)msg).getMessage());
                }
                break;
            case USER_CREATE:
                System.out.println("You have successfully registered");
                break;
        }
    }

    private void processResult(Session session, Message msg) {
        CommandResult result = ((CommandResultMessage) msg).getCommandResult();
        switch (result.getStatus()) {
            case OK:
                writeOKResult(result);
                break;
            case FAILED:
                System.out.println("FAILED: " + result.getReport());
                break;
            case NOT_LOGGED:
                System.out.println("This command needs in logged user");
                break;
            case WARNING:
                System.out.println("WARNING: " + result.getReport());
                break;
        }

        System.out.print("$");
    }

    @Override
    public void onMessage(Session session, Message msg) {
        if (msg.getType() == CommandType.COMMAND_RESULT) {
            log.info("Get message result. " + ((CommandResultMessage) msg).getCommandResult().getCommandType());
            if (msg.getType() == CommandType.COMMAND_RESULT) {
                processResult(session, msg);
            }
        } else if (msg.getType() == CommandType.CHAT_SEND) {
            SendMessage m = (SendMessage)msg;
            System.out.println("Chat " +  m.getChatId() +
                    ", user " + m.getSenderId() +
                    ", message " + m.getMessage());
        } else {
            log.warn("onMessage: accept unexpected object");
        }
    }

    public static void main(String[] args) throws Exception{
        Protocol protocol = new NativeSerializeProtocol();
        ThreadedClient client = new ThreadedClient(protocol);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$");
            String input = scanner.nextLine();
            if ("q".equals(input)) {
                return;
            }
            client.processInput(input);
        }
    }

}
