package test.ru.mail.track.socket_messenger.serialization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.*;
import ru.mail.track.socket_messenger.serialization.NativeSerializeProtocol;
import ru.mail.track.socket_messenger.serialization.Protocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NativeSerializationTest extends Assert {
    private final Map<CommandType, Message> messages = new HashMap<>();

    @Before
    public void setup() {
        LoginMessage login = new LoginMessage("Jack", "qwerty");
        login.setSenderId(123L);
        messages.put(CommandType.USER_LOGIN, login);

        SendMessage send = new SendMessage(1L, "Hell!");
        messages.put(CommandType.CHAT_SEND, send);

        Long[] parts = {1L, 2L, 3L};
        ChatCreateMessage chatCreateMessage = new ChatCreateMessage(new ArrayList<>(Arrays.asList(parts)));
        messages.put(CommandType.CHAT_CREATE, chatCreateMessage);

        ChatFindMessage chatFindMessage = new ChatFindMessage(3L, "0*.{1, 3}");
        messages.put(CommandType.CHAT_FIND, chatFindMessage);

        ChatHistoryMessage chatHistoryMessage = new ChatHistoryMessage(1L, 3);
        messages.put(CommandType.CHAT_HISTORY, chatHistoryMessage);

        ChatListMessage chatListMessage = new ChatListMessage();
        messages.put(CommandType.CHAT_LIST, chatListMessage);

        CommandResultMessage commandResultMessage = new CommandResultMessage(
                new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.OK, null));
        messages.put(CommandType.COMMAND_RESULT, commandResultMessage);

        HelpMessage helpMessage = new HelpMessage();
        messages.put(CommandType.USER_HELP, helpMessage);

        NicknameMessage nicknameMessage = new NicknameMessage("vano777");
        messages.put(CommandType.NICKNAME_COMMAND, nicknameMessage);

        UserCreateMessage userCreateMessage = new UserCreateMessage("pwq1212w", "wdsasddsad");
        messages.put(CommandType.USER_CREATE, userCreateMessage);

        UserInfoMessage userInfoMessage = new UserInfoMessage(2L);
        messages.put(CommandType.USER_INFO, userInfoMessage);

        UserPassMessage userPassMessage = new UserPassMessage("1212313", "121212");
        messages.put(CommandType.USER_PASS, userPassMessage);
    }

    @Test
    public void encodeLogin() throws Exception {
        Message origin = messages.get(CommandType.USER_LOGIN);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }


    @Test
    public void encodeChatCreate() throws Exception {
        Message origin = messages.get(CommandType.CHAT_CREATE);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeChatFind() throws Exception {
        Message origin = messages.get(CommandType.CHAT_FIND);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeChatHistory() throws Exception {
        Message origin = messages.get(CommandType.CHAT_HISTORY);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeChatList() throws Exception {
        Message origin = messages.get(CommandType.CHAT_LIST);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeCommandResult() throws Exception {
        Message origin = messages.get(CommandType.COMMAND_RESULT);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeHelp() throws Exception {
        Message origin = messages.get(CommandType.USER_HELP);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeNickname() throws Exception {
        Message origin = messages.get(CommandType.NICKNAME_COMMAND);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeUserCreate() throws Exception {
        Message origin = messages.get(CommandType.USER_CREATE);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodeUserInfo() throws Exception {
        Message origin = messages.get(CommandType.USER_INFO);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }

    @Test
    public void encodePass() throws Exception {
        Message origin = messages.get(CommandType.USER_PASS);
        Protocol protocol = new NativeSerializeProtocol();
        byte[] data = protocol.encode(origin);
        Message copy = protocol.decode(data);
        System.out.println(copy);

        assertTrue(copy.equals(origin));
    }
}
