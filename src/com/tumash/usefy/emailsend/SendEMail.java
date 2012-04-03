package com.tumash.usefy.emailsend;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEMail {
    /**
     *
     * @param body - message body.
     * @param recipient - receiver.
     * @param internetAddress - e-mail receiver.
     */
   public static void send(String body, String recipient, String internetAddress) throws EMailException {
       Properties property = new Properties();
       Session session = Session.getDefaultInstance(property);
       Message mess = new MimeMessage(session);
       try {
           mess.setFrom(new InternetAddress("dmitrytdmdima@gmail.com", "Coming Soon Page"));
           mess.addRecipient(Message.RecipientType.TO,
                   new InternetAddress(internetAddress, recipient));
           mess.setSubject("Your e-mail is added!");
           mess.setText(body);
           Transport.send(mess);
       } catch (MessagingException e) {
            throw new EMailException(e.getMessage());
       } catch (UnsupportedEncodingException e) {
           throw new EMailException(e.getMessage());
       }

   }
}
