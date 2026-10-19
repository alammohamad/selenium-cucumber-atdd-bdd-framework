package com.company.qa.notifications;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailNotification {

    public static void sendEmail(
            String subject,
            String body) throws Exception {

        String gmailUsername = System.getenv("GMAIL_USERNAME");
        String gmailAppPassword = System.getenv("GMAIL_APP_PASSWORD");
        String recipient = System.getenv("QA_EMAIL_RECIPIENT");

        if (gmailUsername == null || gmailAppPassword == null
                || recipient == null) {
            throw new IllegalStateException(
                "Email configuration missing. Check Windows environment variables.");
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        gmailUsername, gmailAppPassword);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(gmailUsername));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(recipient));
        message.setSubject(subject);
        String executionSource = getExecutionSource();
        message.setText("Execution Source: " + executionSource + "\n\n" + body);

        Transport.send(message);

        System.out.println("Email notification sent successfully.");
    }
    
    private static String getExecutionSource() {
        if (System.getenv("JENKINS_URL") != null) {
            return "Jenkins";
        }

        if (System.getenv("GITHUB_ACTIONS") != null) {
            return "GitHub Actions";
        }

        return "Local PC";
    }

}