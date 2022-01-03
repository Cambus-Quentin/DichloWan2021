package com.dichlowan.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("dichlowan")
public class DichlowanProperties {
    public static class Scrapper {
        private int initialDelay;
        private int fixedRate;

        public int getInitialDelay() {
            return initialDelay;
        }

        public void setInitialDelay(int initialDelay) {
            this.initialDelay = initialDelay;
        }

        public int getFixedRate() {
            return fixedRate;
        }

        public void setFixedRate(int fixedRate) {
            this.fixedRate = fixedRate;
        }
    }
    private Scrapper scrapper;

    public static class Mail {
        private String from;
        private String to;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
    private Mail mail;

    public Scrapper getScrapper() {
        return this.scrapper;
    }

    public void setScrapper(Scrapper scrapper) {
        this.scrapper = scrapper;
    }

    public Mail getMail() {
        return this.mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
