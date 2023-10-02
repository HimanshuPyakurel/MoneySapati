package global.citytech.moneyexchange.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

    public void sendEmail(String message, String subject, String to, String from ){

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("himansupyakurel90@gmail.com","karuyidqaltmkens");
            }
        });

        session.setDebug(true);

        //compose the message
        MimeMessage mimeMessage =  new MimeMessage(session);

        //from email
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Send successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
