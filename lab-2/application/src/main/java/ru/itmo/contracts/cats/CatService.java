package ru.itmo.contracts.cats;

import ru.itmo.models.Cat;
import ru.itmo.models.Colors;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

public interface CatService {
    List<Cat> getAllCats();

    Cat addCat(String name, LocalDate birthDate, String breed, Colors color, Owner owner);

    void addFriend(Cat firstCat, Cat secondCat);

    void deleteFriend(Cat firstCat, Cat secondCat);

    Cat getCatById(int id);

    Cat getCatByName(String name);

    List<Cat> getCatsByOwner(Owner idOwner);

    List<Cat> getCatFriends(Cat cat);

    void deleteCat(Cat cat);

}
