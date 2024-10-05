package ru.itmo.clients;

import ru.itmo.CatColour;
import ru.itmo.CatDto;

import java.util.List;

public interface CatClient {

    CatDto findCatById(int id, String requestSenderName);

    List<CatDto> findCatsByOwnerName(String name);

    List<CatDto> findCatsByOwnerNameAndName(String ownerName, String catName);

    List<CatDto> findCatsByColour(CatColour colour, String requestSenderName);

    List<CatDto> findCatsByBreed(String breed, String requestSenderName);

    CatDto saveCat(CatDto dto, String requestSenderName);

    CatDto updateCat(CatDto dto, String requestSenderName);

    void deleteCatById(int id, String requestSenderName);

    void befriendCats(int lId, int rId, String requestSenderName);
}
