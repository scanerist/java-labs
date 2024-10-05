package ru.itmo.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCatOwnerClientConfiguration {

    private final String url;

    public WebCatOwnerClientConfiguration() {
        this.url = "http://localhost:8081";
    }

    @Bean(value = "catOwnerWebClient")
    public WebClient webCatOwnerClient() {

        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
