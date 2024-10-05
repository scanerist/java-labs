package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.CatBefriendDto;
import ru.itmo.CatColour;
import ru.itmo.CatDtoExtended;
import ru.itmo.models.Cat;
import ru.itmo.models.CatDtoConverter;
import ru.itmo.repositories.CatRepositoryJpa;

import java.util.*;

@Service
public class CatServiceImpl implements CatService {

    private CatRepositoryJpa catRepository;
    private CatDtoConverter converter;

    @Autowired
    public CatServiceImpl(CatRepositoryJpa repository, CatDtoConverter converter) {
        this.catRepository = repository;
        this.converter = converter;
    }

    @Override
    @KafkaListener(topics = "topicCatCreate", groupId = "group4", containerFactory = "catKafkaListenerContainerFactory")
    public CatServiceResult saveCat(CatDtoExtended dto) {

        if (!Objects.equals(dto.getCatDto().getOwnerName(), dto.getRequestSenderName()))
            return new CatServiceResult.AccessDenied();

        List<Cat> friends = new ArrayList<>();
        for (var id : dto.getCatDto().getCatFriends()) {
            friends.add(catRepository.getReferenceById(id));
        }
        return new CatServiceResult.Success(
                Collections.singletonList(
                        converter.toDto(catRepository.save(converter.toModel(dto.getCatDto(), friends)))));
    }

    @Override
    public CatServiceResult readCat(int id, String requestSenderName) {

        Cat cat = catRepository.getReferenceById(id);
        if (!Objects.equals(cat.getOwner(), requestSenderName))
            return new CatServiceResult.AccessDenied();

        return new CatServiceResult.Success(
                Collections.singletonList(converter.toDto(cat)));
    }

    @Override
    public CatServiceResult findCatsByOwnerName(String ownerName) {

        return new CatServiceResult.Success(
                catRepository.findCatsByOwner(ownerName).stream().map(converter::toDto).toList());
    }

    @Override
    public CatServiceResult findCatsByOwnerNameAndName(String ownerName, String catName) {

        return new CatServiceResult.Success(
                catRepository.findByOwnerAndName(ownerName, catName).stream().map(converter::toDto).toList());
    }

    @Override
    public CatServiceResult findCatsByColour(CatColour colour) {

        return new CatServiceResult.Success(
                catRepository.findCatsByColour(colour).stream().map(converter::toDto).toList());
    }

    @Override
    public CatServiceResult findCatsByBreed(String breed) {

        return new CatServiceResult.Success(
                catRepository.findCatsByBreed(breed).stream().map(converter::toDto).toList());
    }

    @Override
    public CatServiceResult findCatsByColourAndOwnerName(CatColour colour, String ownerName) {

        return new CatServiceResult.Success(
                catRepository
                        .findCatsByColourAndOwner(colour, ownerName).stream().map(converter::toDto).toList());
    }

    @Override
    public CatServiceResult findCatsByBreedAndOwnerName(String breed, String ownerName) {

        return new CatServiceResult.Success(
                catRepository
                        .findCatsByBreedAndOwner(breed, ownerName).stream().map(converter::toDto).toList());
    }

    @Override
    @KafkaListener(topics = "topicCatUpdate", groupId = "group5", containerFactory = "catKafkaListenerContainerFactory")
    public CatServiceResult updateCat(CatDtoExtended dto) {

        if (!Objects.equals(dto.getCatDto().getOwnerName(), dto.getRequestSenderName()))
            return new CatServiceResult.AccessDenied();

        List<Cat> friends = new ArrayList<>();
        for (var id : dto.getCatDto().getCatFriends()){
            friends.add(catRepository.getReferenceById(id));
        }
        return new CatServiceResult.Success(
                Collections.singletonList(converter
                        .toDto(catRepository.save(converter.toModel(dto.getCatDto(), friends)))));
    }

    @Override
    @KafkaListener(topics = "topicCatDelete", groupId = "group6", containerFactory = "catKafkaListenerContainerFactory")
    public CatServiceResult deleteCat(CatDtoExtended dto) {

        var cat = catRepository.getReferenceById(dto.getCatDto().getId());
        if (!Objects.equals(cat.getOwner(), dto.getRequestSenderName()))
            return new CatServiceResult.AccessDenied();

        catRepository.deleteById(dto.getCatDto().getId());
        return new CatServiceResult.Success(new ArrayList<>());
    }

    @Override
    @KafkaListener(topics = "topicCatBefriend", groupId = "group7", containerFactory = "catBefriendKafkaListenerContainerFactory")
    @Transactional
    public CatServiceResult befriendCats(CatBefriendDto dto) {
        Cat leftCat = catRepository.getReferenceById(dto.getLCatId());
        Cat rightCat = catRepository.getReferenceById(dto.getRCatId());

        if (!Objects.equals(leftCat.getOwner(), dto.getRequestSenderName())
                && !Objects.equals(rightCat.getOwner(), dto.getRequestSenderName()))
            return new CatServiceResult.AccessDenied();

        leftCat.getCatFriends().add(rightCat);
        rightCat.getCatFriends().add(leftCat);

        catRepository.save(leftCat);
        catRepository.save(rightCat);
        return new CatServiceResult.Success(
                Arrays.asList(converter.toDto(leftCat), converter.toDto(rightCat)));
    }
}
