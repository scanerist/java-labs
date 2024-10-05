package ru.itmo.abstractions;

import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.util.List;

public interface CatRepository {
    List<Cat> index();

    Cat getById(int id);

    Cat getByName(String name);

    List<Cat> getCatsByOwner(Owner owner);

    List<Cat> getCatFriends(Cat cat);

    void save(Cat cat);

    void delete(Cat cat);

    void deleteFriend(Cat firstCat, Cat secondCat);

    void addFriend(Cat firstCat, Cat secondCat);
}
