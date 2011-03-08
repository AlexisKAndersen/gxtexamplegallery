/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ratul.gxtexamplegalary.server;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author ratul
 */
public class MailUtility
{
    public static void sendMail(String body, String subject, String[] receivers)
    {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try 
        {
        	InternetAddress[] receiverAddress = new InternetAddress[receivers.length];
        	for(int i=0; i< receivers.length; i++)
        	{
        		receiverAddress[i] = new InternetAddress(receivers[i]);
        	}
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("szawoad.appengine@gmail.com"));
            msg.addRecipients(Message.RecipientType.TO, receiverAddress);
            msg.setSubject(subject);
            //msg.setText(body);
            msg.setContent(body, "text/html");
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
        
    }
}
