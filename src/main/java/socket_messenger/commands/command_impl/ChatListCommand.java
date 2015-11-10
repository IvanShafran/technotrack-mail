package main.java.socket_messenger.commands.command_impl;

import main.java.socket_messenger.chat.ChatStore;
import main.java.socket_messenger.commands.Command;
import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.commands.command_result.ChatListCommandResult;
import main.java.socket_messenger.commands.command_result.CommandResult;
import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.session.Session;
import main.java.socket_messenger.user.User;

/**
 * Created by Ivan Shafran on 10.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatListCommand implements Command {
    private ChatStore chatStore;

    public ChatListCommand(ChatStore chatStore) {
        this.chatStore = chatStore;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        User user = session.getSessionUser();
        if (user == null) {
            return new CommandResult(CommandType.CHAT_LIST, CommandResult.Status.NOT_LOGGED, null);
        }

        Long id = user.getId();

        return new ChatListCommandResult(CommandResult.Status.OK, null, chatStore.getChatsByUserId(id));
    }

    @Override
    public String getUsage() {
        return "/chat_list";
    }
}
