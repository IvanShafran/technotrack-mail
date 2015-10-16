package ru.mail.track.messenger.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ivan Shafran on 16.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class MessageImpl implements Message {
    private String text;
    private Date date;
    SimpleDateFormat dateFormat;

    public MessageImpl(Date date, String text) {
        this.date = date;
        this.text = text;
        dateFormat = new SimpleDateFormat("hh:mm:ss");
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return dateFormat.format(date) + " " + text;
    }
}
