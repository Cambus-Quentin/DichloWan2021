package com.dichlowan.backend.service;

import com.dichlowan.backend.configuration.DichlowanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class EmailService {
    @Autowired
    DichlowanProperties dichlowanProperties;

    private String FROM;
    private String TO;

    @Autowired
    private JavaMailSender emailSender;

    @PostConstruct
    public void postConstruct() {
        this.FROM = dichlowanProperties.getMail().getFrom();
        this.TO = dichlowanProperties.getMail().getTo();
    }

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
