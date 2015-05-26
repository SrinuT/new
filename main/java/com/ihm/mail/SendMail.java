package com.ihm.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * <p>
 *     this class initialize by spring container and has methods for send emails
 * </p>
 *
 * @author Artur Yolchyan
 */
public class SendMail {

    private MailSender mailSender;

    /**
     * this method for sending simple email
     *
     * @param from - String
     * @param to - String
     * @param subject - String
     * @param msg - String
     */
    public void send(String from, String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);

        mailSender.send(message);
    }


    public MailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
