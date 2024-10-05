package ru.itmo.services;

import ru.itmo.CatBefriendDto;
import ru.itmo.CatColour;
import ru.itmo.CatDto;
import ru.itmo.CatDtoExtended;

public interface CatService {

    CatServiceResult saveCat(CatDtoExtended dto);

    CatServiceResult readCat(int id, String requestSenderName);

    CatServiceResult findCatsByOwnerName(String ownerName);

    CatServiceResult findCatsByOwnerNameAndName(String ownerName, String catName);

    CatServiceResult findCatsByColour(CatColour colour);

    CatServiceResult findCatsByBreed(String breed);

    CatServiceResult findCatsByColourAndOwnerName(CatColour colour, String ownerName);

    CatServiceResult findCatsByBreedAndOwnerName(String breed, String ownerName);

    CatServiceResult updateCat(CatDtoExtended dto);

    CatServiceResult deleteCat(CatDtoExtended dto);

    CatServiceResult befriendCats(CatBefriendDto dto);
}
