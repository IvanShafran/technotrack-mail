package main.java.socket_messenger.commands.command_result;

import main.java.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatCreateCommandResult extends CommandResult {
    private Long chatId;

    public ChatCreateCommandResult(Status status, String report, Long chatId) {
        super(CommandType.CHAT_CREATE, status, report);
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
