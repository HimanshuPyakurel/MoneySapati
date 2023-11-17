package global.citytech.moneyexchange.platform.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    public static void sendEmail(String from, String to, String message, String subject){
        String host = "smtp.gmail.com";
        Properties properties = new Properties();
        System.out.println("PROPERTIES " + properties);
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("himansupyakurel90@gmail.com","civupwyvtgqpbwmg");
            }
        });

        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);

        try{
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            Transport.send(m);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
