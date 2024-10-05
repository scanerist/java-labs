package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.clients.CatClient;
import ru.itmo.CatDto;
import ru.itmo.CatOwnerDto;
import ru.itmo.models.CatOwnerDtoConverter;
import ru.itmo.repositories.CatOwnerRepositoryJpa;

import java.util.List;

@Service
public class CatOwnerServiceImpl implements CatOwnerService {

    private CatClient catClient;
    private CatOwnerRepositoryJpa repository;
    private CatOwnerDtoConverter catOwnerDtoConverter;

    @Autowired
    public CatOwnerServiceImpl(CatClient catClient, CatOwnerRepositoryJpa repository, CatOwnerDtoConverter catOwnerDtoConverter) {
        this.catClient = catClient;
        this.repository = repository;
        this.catOwnerDtoConverter = catOwnerDtoConverter;
    }

    @Override
    @KafkaListener(topics = "topicOwnerCreate", groupId = "group1", containerFactory = "ownerKafkaListenerContainerFactory")
    public CatOwnerDto saveCatOwner(CatOwnerDto dto) {

        List<CatDto> ownedCats = catClient.findCatsByOwnerName(dto.getName());
        return catOwnerDtoConverter
                .toDto(repository.save(catOwnerDtoConverter.toModel(dto)), ownedCats);
    }

    @Override
    public CatOwnerDto readCatOwner(String name) {

        List<CatDto> ownedCats = catClient.findCatsByOwnerName(name);

        return catOwnerDtoConverter.toDto(repository.getReferenceById(name), ownedCats);
    }

    @Override
    @KafkaListener(topics = "topicOwnerUpdate", groupId = "group2", containerFactory = "ownerKafkaListenerContainerFactory")
    public CatOwnerDto updateCatOwner(CatOwnerDto dto) {

        List<CatDto> ownedCats = catClient.findCatsByOwnerName(dto.getName());
        return catOwnerDtoConverter
                .toDto(repository.save(catOwnerDtoConverter.toModel(dto)), ownedCats);
    }

    @Override
    @KafkaListener(topics = "topicOwnerDelete", groupId = "group3", containerFactory = "ownerKafkaListenerContainerFactory")
    public void deleteCatOwner(CatOwnerDto dto) {

        repository.deleteById(dto.getName());
    }
}
