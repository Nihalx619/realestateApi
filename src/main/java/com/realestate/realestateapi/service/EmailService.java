package com.realestate.realestateapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.realestate.realestateapi.entity.Enquiry;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.notification.email}")
    private String notificationEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEnquiryNotification(Enquiry enquiry) {
        try {
            String projectName = enquiry.getProject() != null
                    ? enquiry.getProject().getName()
                    : "General Enquiry";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(notificationEmail);
            message.setSubject("New Enquiry: " + projectName);
            message.setText(
                "You have a new enquiry!\n\n" +
                "Project: " + projectName + "\n" +
                "Name: " + enquiry.getName() + "\n" +
                "Phone: " + enquiry.getPhone() + "\n" +
                "Email: " + (enquiry.getEmail() != null ? enquiry.getEmail() : "Not provided") + "\n" +
                "Message: " + (enquiry.getMessage() != null ? enquiry.getMessage() : "None") + "\n\n" +
                "Received at: " + enquiry.getCreatedAt() + "\n\n" +
                "Log in to the admin panel to follow up."
            );
            mailSender.send(message);
        } catch (Exception e) {
            // if email fails, don't crash the enquiry — it's already saved in DB
            System.err.println("Failed to send enquiry notification email: " + e.getMessage());
        }
    }
}