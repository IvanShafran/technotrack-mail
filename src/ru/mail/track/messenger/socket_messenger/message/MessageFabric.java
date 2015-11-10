package ru.mail.track.messenger.socket_messenger.message;

import com.sun.istack.internal.Nullable;
import ru.mail.track.messenger.socket_messenger.message.message_impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Shafran on 05.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class MessageFabric {
    public static Message createHelpMessage(String[] tokens) {
        return new HelpMessage();
    }

    public static Message createLoginMessage(String[] tokens) {
        String login = null;
        if (tokens.length >= 2) {
            login = tokens[1];
        }
        String pass = null;
        if (tokens.length >= 3) {
            pass = tokens[2];
        }

        return new LoginMessage(login, pass);
    }

    @Nullable
    public static Message createSendMessage(String[] tokens) {
        Long chatId = null;
        if (tokens.length >= 2) {
            try {
                chatId = Long.valueOf(tokens[1]);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        String message;
        if (tokens.length >= 3) {
            message = tokens[2];
        } else {
            return null;
        }

        return new SendMessage(chatId, message);
    }

    @Nullable
    public static Message createChatCreateMessage(String[] tokens) {
        List<Long> users = new ArrayList<>();

        if (tokens.length >= 2) {
            for (String friendId : tokens[1].split(",")) {
                try {
                    users.add(Long.valueOf(friendId));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        return new ChatCreateMessage(users);
    }

    public static Message createNicknameMessage(String[] tokens) {
        String nickname = null;
        if (tokens.length >= 2) {
            nickname = tokens[1];
        }

        return new NicknameMessage(nickname);
    }

    @Nullable
    public static Message createUserInfo(String[] tokens) {
        Long id = null;
        if (tokens.length >= 2) {
            try {
                id = Long.valueOf(tokens[1]);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return new UserInfoMessage(id);
    }

    @Nullable
    public static Message createUserPassMessage(String[] tokens) {
        if (tokens.length < 3) {
            return null;
        }

        return new UserPassMessage(tokens[1], tokens[2]);
    }

    public static Message createChatListMessage(String[] tokens) {
        return new ChatListMessage();
    }

    @Nullable
    public static Message createChatHistoryMessage(String[] tokens) {
        Long chatId;
        if (tokens.length >= 2) {
            try {
                chatId = Long.valueOf(tokens[1]);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }

        Integer number = null;
        if (tokens.length >= 3) {
            try {
                number = Integer.valueOf(tokens[2]);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return new ChatHistoryMessage(chatId, number);
    }

    public static Message createChatFindMessage(String[] tokens) {
        Long chatId;
        if (tokens.length >= 2) {
            try {
                chatId = Long.valueOf(tokens[1]);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }

        String regex = null;
        if (tokens.length >= 3) {
            regex = tokens[2];
        }

        return new ChatFindMessage(chatId, regex);
    }

    @Nullable
    public static Message createMessage(String[] tokens) {
        if (tokens.length == 0) {
            return null;
        }

        String cmdType = tokens[0];
        switch (cmdType) {
            case "/help":
                return createHelpMessage(tokens);
            case "/login":
                return createLoginMessage(tokens);
            case "/chat_send":
                return createSendMessage(tokens);
            case "/chat_create":
                return createChatCreateMessage(tokens);
            case "/user":
                return createNicknameMessage(tokens);
            case "/user_info":
                return createUserInfo(tokens);
            case "/user_pass":
                return createUserPassMessage(tokens);
            case "/chat_list":
                return createChatListMessage(tokens);
            case "/chat_history":
                return createChatHistoryMessage(tokens);
            case "/chat_find":
                return createChatFindMessage(tokens);
            default:
                return null;
        }
    }
}
