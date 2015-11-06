package ru.mail.track.messenger.chat.commands;

import java.util.ArrayList;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public interface Command {
    String getName();
    void execute(String[] args);
    String getUsage();
}
