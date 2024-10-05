package ru.itmo.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.itmo.CatOwnerDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebCatOwnerClient implements CatOwnerClient {

    private WebClient webCatOwnerClient;
    private KafkaTemplate<String, CatOwnerDto> kafkaTemplate;

    @Autowired
    public WebCatOwnerClient(
            @Qualifier("catOwnerWebClient") WebClient webCatOwnerClient,
            @Qualifier("catOwnerKafkaTemplate") KafkaTemplate<String, CatOwnerDto> kafkaTemplate) {
        this.webCatOwnerClient = webCatOwnerClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public CatOwnerDto findCatOwnerByName(String name) {

        return webCatOwnerClient
                .get()
                .uri(String.join("", "/catOwners/", name))
                .retrieve()
                .bodyToMono(CatOwnerDto.class)
                .block();
    }

    @Override
    public CatOwnerDto saveCatOwner(CatOwnerDto dto) {

        kafkaTemplate.send("topicOwnerCreate", dto);
        return dto;
    }

    @Override
    public CatOwnerDto updateCatOwner(CatOwnerDto dto) {

        kafkaTemplate.send("topicOwnerUpdate", dto);
        return dto;
    }

    @Override
    public void deleteCatOwnerByName(String name) {

        var dto = new CatOwnerDto();
        dto.setName(name);
        kafkaTemplate.send("topicOwnerDelete", dto);
    }
}
