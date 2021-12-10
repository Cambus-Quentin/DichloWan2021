package com.dichlowan.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static String FROM = "noreply@dichlowan.app";
    private static String TO = "receiver@domaine.fr";

    @Autowired
    private JavaMailSender emailSender;

    public void sendAlert() {
        this.sendSimpleMessage(
                this.TO,
                "Dichlowan - Alert",
                "Le seuil de dychlorométhane a été dépassé. Le capteur l'ayant détecté est le cap-XXXX");
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.FROM);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
