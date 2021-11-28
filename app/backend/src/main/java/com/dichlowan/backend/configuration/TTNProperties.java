package com.dichlowan.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("network.ttn")
public class TTNProperties {
    public static String NETWORK_NAME = "TTN";

    public static class Api {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    private Api api;

    public static class App {
        private String id;

        public static class Auth {
            private String bearerToken;

            public String getBearerToken() {
                return bearerToken;
            }

            public void setBearerToken(String bearerToken) {
                this.bearerToken = bearerToken;
            }
        }
        private Auth auth;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Auth getAuth() {
            return auth;
        }

        public void setAuth(Auth auth) {
            this.auth = auth;
        }
    }
    private App app;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
