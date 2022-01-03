package com.dichlowan.backend.service;

import com.dichlowan.backend.configuration.DichlowanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class EmailService {
    @Autowired
    DichlowanProperties dichlowanProperties;

    private String FROM;
    private String TO;
    private String FRONT_URL;

    @Autowired
    private JavaMailSender emailSender;

    @PostConstruct
    public void postConstruct() {
        this.FROM = dichlowanProperties.getMail().getFrom();
        this.TO = dichlowanProperties.getMail().getTo();
        this.FRONT_URL = dichlowanProperties.getFrontUrl();
    }

    public void sendAlert(Set<String> captorName) {
        String msg = "Le seuil de dychlorométhane a été dépassé.";

        if (captorName.size() > 1) {
            msg += "Les capteurs l'ayant détecté sont : ";
        } else {
            msg += "Le capteur l'ayant détecté est le ";
        }

        for(String s : captorName) {
            msg += s + ", ";
        }

        msg += "\n\nPour plus d'information, veuillez vous rendre sur le site suivant : "+this.FRONT_URL;

        this.sendSimpleMessage(
                this.TO,
                "Dichlowan - Alert",
                msg
        );
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
