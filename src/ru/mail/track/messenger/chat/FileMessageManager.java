package ru.mail.track.messenger.chat;

import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Shafran on 16.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class FileMessageManager implements MessageManager {
    //root to message files; root/from/to.chat
    private String root;
    //from -> to -> messages
    private Map<String, Map<String, List<Message>>> messages;
    //from -> to -> bufferedWriterToFile
    private Map<String, Map<String, BufferedWriter>> bufferedWriterToFile;

    @Nullable
    private List<Message> readMessagesFromFile(String path) throws FileMessageManagerCreatingException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Message> messages = new ArrayList<Message>();

            String line;
            while (br.ready()) {
                line = br.readLine();

                String[] strings = line.split(" ");

                if (strings.length != 2) {
                    throw new FileMessageManagerCreatingException("File " + path + " destroyed");
                }

                long time;
                try {
                    time = Long.parseLong(strings[0]);
                } catch (NumberFormatException e) {
                    throw new FileMessageManagerCreatingException("File " + path + " destroyed");
                }

                String text = strings[1];

                messages.add(new MessageImpl(new Date(time), text));
            }

            return messages;
        } catch (IOException e) {
            return null;
        }
    }

    private void readMessages(File rootFile) throws FileMessageManagerCreatingException {
        if (rootFile.listFiles() == null) {
            return;
        }

        for (File fromFile : rootFile.listFiles()) {
            if (!fromFile.isDirectory() || fromFile.listFiles() == null) {
                continue;
            }

            String from = fromFile.getName();
            this.messages.put(from, new HashMap<>());
            this.bufferedWriterToFile.put(from, new HashMap<>());

            for (File toFile : fromFile.listFiles()) {
                if (!toFile.isFile() || !toFile.getName().matches(".+\\.chat")) {
                    continue;
                }

                String to = toFile.getName().substring(0, toFile.getName().length() - ".chat".length());

                List<Message> messages = readMessagesFromFile(toFile.getAbsolutePath());
                BufferedWriter bufferedWriter;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(toFile.getAbsolutePath(), true));
                } catch (IOException e) {
                    bufferedWriter = null;
                }

                if (messages != null && bufferedWriter != null) {
                    this.messages.get(from).put(to, messages);
                    this.bufferedWriterToFile.get(from).put(to, bufferedWriter);
                } else {
                    throw new FileMessageManagerCreatingException("Can't read " + toFile.getAbsolutePath());
                }
            }
        }
    }

    public FileMessageManager(String root) throws FileMessageManagerCreatingException {
        messages = new HashMap<>();
        bufferedWriterToFile = new HashMap<>();

        this.root = root;

        File rootFile = new File(root);
        if (!rootFile.exists()) {
            if (!rootFile.mkdirs()) {
                throw new FileMessageManagerCreatingException("Root file can't be created");
            }
        } else {
            readMessages(rootFile);
        }
    }

    private void createChatFiles(String from, String to) throws AddMessageException {
        if (!messages.containsKey(from)) {
            File file = new File(root + File.separator + from);
            if (!file.mkdir()) {
                throw new AddMessageException("File " + file.getAbsolutePath() + " can't be created");
            }

            messages.put(from, new HashMap<>());
            bufferedWriterToFile.put(from, new HashMap<>());
        }

        if (!messages.get(from).containsKey(to)) {
            File file = new File(root + File.separator + from + File.separator + to + ".chat");
            try {
                if (!file.createNewFile()) {
                    throw new AddMessageException("File " + file.getAbsolutePath() + " can't be created");
                }
            } catch (IOException e) {
                throw new AddMessageException("File " + file.getAbsolutePath() + " can't be created");
            }

            messages.get(from).put(to, new ArrayList<>());

            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
            } catch (IOException e) {
                throw new AddMessageException("File descriptor to" + file.getAbsolutePath() + " can't be created");
            }
            bufferedWriterToFile.get(from).put(to, bufferedWriter);
        }
    }

    @Override
    public void addMessage(String from, String to, Message message) throws AddMessageException {
        createChatFiles(from, to);

        messages.get(from).get(to).add(message);
        try {
            bufferedWriterToFile.get(from).get(to).write(message.getDate().getTime() + " " + message.getText() + "\n");
        } catch (IOException e) {
            throw new AddMessageException("Can't write message to bufferedWriter");
        }
    }

    @Override
    @Nullable
    public List<Message> getMessages(String from, String to) {
        if (!messages.containsKey(from) || !messages.get(from).containsKey(to)) {
            return null;
        } else {
            return messages.get(from).get(to);
        }
    }

    @Override
    public void close() {
        for (Map<String, BufferedWriter> map : bufferedWriterToFile.values()) {
            for (BufferedWriter writer : map.values()) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("BufferedWriter cause exception during closing");
                }
            }
        }
    }
}
