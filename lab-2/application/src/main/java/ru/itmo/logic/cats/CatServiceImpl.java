package ru.itmo.logic.cats;

import ru.itmo.abstractions.CatRepository;
import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.index();
    }

    @Override
    public Cat addCat(String name, LocalDate birthDate, String breed, Colors color, Owner owner) {
        Cat cat = new Cat(name, birthDate, breed, color, owner);
        catRepository.save(cat);
        return cat;
    }

    @Override
    public void addFriend(Cat firstCat, Cat secondCat) {
        firstCat.addFriend(secondCat);
        catRepository.addFriend(firstCat, secondCat);
    }

    @Override
    public void deleteFriend(Cat firstCat, Cat secondCat) {
        firstCat.removeFriend(secondCat);
        catRepository.deleteFriend(firstCat, secondCat);
    }

    @Override
    public Cat getCatById(int id) {
        return catRepository.getById(id);
    }

    @Override
    public Cat getCatByName(String name) {
        return catRepository.getByName(name);
    }

    @Override
    public List<Cat> getCatsByOwner(Owner owner) {
        return catRepository.getCatsByOwner(owner);
    }

    @Override
    public List<Cat> getCatFriends(Cat cat) {
        return catRepository.getCatFriends(cat);
    }

    @Override
    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }
}
