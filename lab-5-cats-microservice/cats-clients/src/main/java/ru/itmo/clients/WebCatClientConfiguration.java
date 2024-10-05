package ru.itmo.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCatClientConfiguration {

    private final String url;

    public WebCatClientConfiguration() {
        this.url = "http://localhost:8082";
    }

    @Bean(value = "catWebClient")
    public WebClient webCatClient() {

        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
