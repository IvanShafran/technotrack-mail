package ru.mail.track.messenger.socket_messenger.commands;

import ru.mail.track.messenger.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.session.Session;

/**
 * Интерфейс для всех команд
 *
 *  То есть, даже имея возможность определить здесь абстрактный метод execute() я предпочту интерфейс
 * потому что интерфейс определяет поведение (свойство)
 *
 * А цель абстрактного класса - переиспользование кода
 */
public interface Command {
    CommandResult execute(Session session, Message message);
    String getUsage();
}
