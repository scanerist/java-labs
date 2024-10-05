package ru.itmo.services;

import ru.itmo.CatOwnerDto;

public interface CatOwnerService {

    CatOwnerDto saveCatOwner(CatOwnerDto dto);

    CatOwnerDto readCatOwner(String name);

    CatOwnerDto updateCatOwner(CatOwnerDto dto);

    void deleteCatOwner(CatOwnerDto dto);
}