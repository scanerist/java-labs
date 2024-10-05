package ru.itmo.models;

import org.springframework.stereotype.Component;
import ru.itmo.CatDto;

import java.util.List;

@Component
public class CatDtoConverter {

    public CatDto toDto(Cat cat) {

        return new CatDto(
                cat.getId(),
                cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColour(),
                cat.getOwner(),
                cat.getCatFriends().stream().map(Cat::getId).toList());
    }

    public Cat toModel(CatDto dto, List<Cat> catFriends) {

        return new Cat(
                dto.getId(),
                dto.getName(),
                dto.getBirthDate(),
                dto.getBreed(),
                dto.getColour(),
                dto.getOwnerName(),
                catFriends);
    }
}
