package ru.itmo.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.itmo.CatBefriendDto;
import ru.itmo.CatColour;
import ru.itmo.CatDto;
import ru.itmo.CatDtoExtended;

import java.util.List;

@Component
public class WebCatClient implements CatClient {

    private WebClient webCatClient;
    private KafkaTemplate<String, CatDtoExtended> kafkaTemplate;
    private KafkaTemplate<String, CatBefriendDto> befriendDtoKafkaTemplate;

    public WebCatClient(@Qualifier("catWebClient") WebClient webCatClient,
                        @Qualifier("catKafkaTemplate") KafkaTemplate<String, CatDtoExtended> kafkaTemplate,
                        @Qualifier("catBefriendKafkaTemplate") KafkaTemplate<String, CatBefriendDto> befriendDtoKafkaTemplate) {
        this.webCatClient = webCatClient;
        this.kafkaTemplate = kafkaTemplate;
        this.befriendDtoKafkaTemplate = befriendDtoKafkaTemplate;
    }

    @Override
    public CatDto findCatById(int id, String requestSenderName) {

        return webCatClient
                .get()
                .uri(String.format("/cats/%s?request_sender=%s", id, requestSenderName))
                .retrieve()
                .bodyToMono(CatDto.class)
                .block();
    }

    @Override
    public List<CatDto> findCatsByOwnerName(String name) {

        return webCatClient
                .get()
                .uri(String.format("/cats?request_sender=%s", name))
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public List<CatDto> findCatsByOwnerNameAndName(String ownerName, String catName) {

        return webCatClient
                .get()
                .uri(String.format("/cats?name=%s&request_sender=%s", catName, ownerName))
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public List<CatDto> findCatsByColour(CatColour colour, String requestSenderName) {

        return webCatClient
                .get()
                .uri(String.format("/cats?colour=%s&request_sender=%s", colour, requestSenderName))
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public List<CatDto> findCatsByBreed(String breed, String requestSenderName) {

        return webCatClient
                .get()
                .uri(String.format("/cats?breed=%s&request_sender=%s", breed, requestSenderName))
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public CatDto saveCat(CatDto dto, String requestSenderName) {

        var dtoExt = new CatDtoExtended(dto, requestSenderName);
        kafkaTemplate.send("topicCatCreate", dtoExt);
        return dtoExt.getCatDto();
    }

    @Override
    public CatDto updateCat(CatDto dto, String requestSenderName) {

        var dtoExt = new CatDtoExtended(dto, requestSenderName);
        kafkaTemplate.send("topicCatUpdate", dtoExt);
        return dtoExt.getCatDto();
    }

    @Override
    public void deleteCatById(int id, String requestSenderName) {

        var dto = new CatDto();
        dto.setId(id);
        var dtoExt = new CatDtoExtended(dto, requestSenderName);
        kafkaTemplate.send("topicCatDelete", dtoExt);
    }

    @Override
    public void befriendCats(int lId, int rId, String requestSenderName) {

        befriendDtoKafkaTemplate.send("topicCatBefriend", new CatBefriendDto(lId, rId, requestSenderName));
    }
}
