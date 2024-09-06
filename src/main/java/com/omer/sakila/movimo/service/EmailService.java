package com.omer.sakila.movimo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPurchaseNotification(String to, String filmTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("omerfarukkilic.17@gmail.com");
        message.setFrom("mailtrap@demomailtrap.com");
        message.setSubject("Purchase Confirmation");
        message.setText("Dear customer,\n\nYou have successfully purchased the film: " + filmTitle + ".\n\nThank you for your purchase!\n\nBest regards,\nMovimo");
        mailSender.send(message);
    }
}