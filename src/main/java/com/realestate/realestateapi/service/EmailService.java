package com.realestate.realestateapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.realestate.realestateapi.entity.Enquiry;

@Service
public class EmailService {

    private static final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";

    @Value("${app.notification.email}")
    private String notificationEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void sendEnquiryNotification(Enquiry enquiry) {
        try {
            String projectName = enquiry.getProject() != null
                    ? enquiry.getProject().getName()
                    : "General Enquiry";

            String body =
                "You have a new enquiry!\n\n" +
                "Project: " + projectName + "\n" +
                "Name: " + enquiry.getName() + "\n" +
                "Phone: " + enquiry.getPhone() + "\n" +
                "Email: " + (enquiry.getEmail() != null ? enquiry.getEmail() : "Not provided") + "\n" +
                "Message: " + (enquiry.getMessage() != null ? enquiry.getMessage() : "None") + "\n\n" +
                "Received at: " + enquiry.getCreatedAt() + "\n\n" +
                "Log in to the admin panel to follow up.";

            Map<String, Object> payload = Map.of(
                "sender", Map.of("email", fromEmail, "name", "MG Realty"),
                "to", List.of(Map.of("email", notificationEmail)),
                "subject", "New Enquiry: " + projectName,
                "textContent", body
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", brevoApiKey);
            headers.set("accept", "application/json");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            restTemplate.postForObject(BREVO_API_URL, request, String.class);

            System.out.println("Enquiry notification email sent to " + notificationEmail);
        } catch (Exception e) {
            System.err.println("Failed to send enquiry notification email: " + e.getMessage());
        }
    }
}