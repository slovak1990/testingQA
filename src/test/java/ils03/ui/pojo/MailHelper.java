package ils03.ui.pojo;

import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.*;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.sleep;

public class MailHelper {
    private final String LOGIN = "***@yandex.ru";
    private final String PASSWORD = "*****";

    @Step("Получение всех непрочитанных сообщений в список")
    public List<String> getAllMailsInInbox() {
        final String HOST_IMAP = "imap.yandex.ru";
        final int PORT_IMAP = 993;
        List<String> lettersList = new ArrayList<>();

        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imap.host", HOST_IMAP);
        properties.setProperty("mail.imap.port", String.valueOf(PORT_IMAP));

        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(HOST_IMAP, PORT_IMAP, LOGIN, PASSWORD);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                String subject = message.getSubject();
                String from = InternetAddress.toString(message.getFrom());
                String content = message.getContent().toString();

                String letter = "Subject: " + subject + "\nFrom: " + from + "\nContent: " + content + "\n--------------------------------------";
                lettersList.add(letter);
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lettersList;
    }

    @Step("Получение значения содержимого письма по отправителю {sender}")
    public String getContentBySender(String sender) {
        final String HOST_IMAP = "imap.yandex.ru";
        final int PORT_IMAP = 993;
        String content = "";
        String link = "";

        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imap.host", HOST_IMAP);
        properties.setProperty("mail.imap.port", String.valueOf(PORT_IMAP));

        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(HOST_IMAP, PORT_IMAP, LOGIN, PASSWORD);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            long startTime = System.currentTimeMillis();
            long endTime = startTime + 20000;

            while (System.currentTimeMillis() < endTime) {
                Message[] messages = inbox.getMessages();

                for (int i = messages.length - 1; i >= 0; i--) {
                    Message message = messages[i];
                    Address[] fromAddresses = message.getFrom();
                    if (fromAddresses != null && fromAddresses.length > 0) {
                        String from = fromAddresses[0].toString();
                        if (from.contains(sender)) {
                            Map<String, String> contentMap = extractContentFromMessage(message);
                            content = contentMap.getOrDefault("Text", "");
                            link = contentMap.getOrDefault("Link", "");
                            inbox.close(false);
                            store.close();
                            return "Ссылка: " + link + "\n\n" + content;
                        }
                    }
                }

                sleep(1000);
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Ссылка: " + link + "\n\n" + content;
    }

    @Step("Удаление писем из ящика по отправителю {sender}")
    public void deleteAllEmailsBySender(String sender) {
        final String HOST_IMAP = "imap.yandex.ru";
        final int PORT_IMAP = 993;

        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imap.host", HOST_IMAP);
        properties.setProperty("mail.imap.port", String.valueOf(PORT_IMAP));

        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(HOST_IMAP, PORT_IMAP, LOGIN, PASSWORD);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                Address[] fromAddresses = message.getFrom();
                if (fromAddresses != null && fromAddresses.length > 0) {
                    String from = fromAddresses[0].toString();
                    if (from.equals(sender)) {
                        message.setFlag(Flags.Flag.DELETED, true);
                    }
                }
            }
            inbox.expunge();
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Метод чтения содержимого почтового ящика")
    private Map<String, String> extractContentFromMessage(Message message) throws MessagingException, IOException {
        Object content = message.getContent();
        Map<String, String> contentMap = new HashMap<>();

        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    String textContent = bodyPart.getContent().toString();
                    contentMap.put("Text", textContent);
                } else if (bodyPart.isMimeType("text/html")) {
                    String htmlContent = bodyPart.getContent().toString();
                    String link = extractLinkFromHtml(htmlContent);
                    if (!link.isEmpty()) {
                        contentMap.put("Link", link);
                    }
                }
            }
        } else if (content instanceof String) {
            String textContent = (String) content;
            contentMap.put("Text", textContent);
        }

        return contentMap;
    }

    @Step("Метод получение ссылки из письма")
    private String extractLinkFromHtml(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        Element linkElement = doc.select("a[href]").first();
        if (linkElement != null) {
            return linkElement.attr("href");
        }
        return "";
    }
}
