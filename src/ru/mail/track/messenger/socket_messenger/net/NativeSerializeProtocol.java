package ru.mail.track.messenger.socket_messenger.net;

import com.sun.istack.internal.Nullable;
import ru.mail.track.messenger.socket_messenger.message.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Ivan Shafran on 01.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class NativeSerializeProtocol implements Protocol {
    @Override
    @Nullable
    public Message decode(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try (ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Message)in.readObject();
        } catch (IOException e) {
            //ooops
            return null;
        } catch (ClassNotFoundException e) {
            //oops
            return null;
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    @Nullable
    public byte[] encode(Message msg) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(msg);
            return bos.toByteArray();
        } catch (IOException e) {
            //ooops
            return null;
        }
    }
}
