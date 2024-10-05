package ru.itmo.models;

import org.springframework.stereotype.Component;
import ru.itmo.CatDto;
import ru.itmo.CatOwnerDto;

import java.util.List;

@Component
public class CatOwnerDtoConverter {

    public CatOwnerDto toDto(CatOwner catOwner, List<CatDto> ownedCats) {

        return new CatOwnerDto(catOwner.getName(), catOwner.getBirthDate(), ownedCats);
    }

    public CatOwner toModel(CatOwnerDto dto) {

        return new CatOwner(dto.getName(), dto.getBirthDate());
    }
}
