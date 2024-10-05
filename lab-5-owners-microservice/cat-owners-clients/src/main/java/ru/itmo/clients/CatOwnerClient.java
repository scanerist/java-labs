package ru.itmo.clients;

import ru.itmo.CatOwnerDto;

public interface CatOwnerClient {

    CatOwnerDto saveCatOwner(CatOwnerDto dto);

    CatOwnerDto findCatOwnerByName(String name);

    CatOwnerDto updateCatOwner(CatOwnerDto dto);

    void deleteCatOwnerByName(String name);
}
