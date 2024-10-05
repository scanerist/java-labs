package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.dto.CatDto;
import ru.itmo.exceptions.UserException;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepository;
import ru.itmo.repositories.OwnerRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


@Service
public class CatService {
    @Autowired
    CatRepository catRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    private UserCheckService userCheckService;

    public Cat addCat(CatDto catDto) {
        Owner owner = ownerRepository.getReferenceById(catDto.getOwnerId());
        Cat cat = new Cat(catDto.getName(), LocalDate.parse(catDto.getBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), catDto.getBreed(), Colors.valueOf(catDto.getColor()), owner);
        //userCheckService.IsUserHaveAuthorityCat(owner.getName(), cat);
        owner.addCat(cat);
        return catRepository.save(cat);
    }

    public void deleteCat(int id, String ownerUsername) {
        userCheckService.IsUserHaveAuthorityCat(ownerUsername, catRepository.getReferenceById(id));
        catRepository.deleteById(id);
    }

    public Cat getCatById(int id, String ownerUsername) {
        Cat cat = catRepository.getReferenceById(id);
        userCheckService.IsUserHaveAuthorityCat(ownerUsername, cat);
        return cat;
    }

    public List<Cat> getAllByName(String name, String ownerUsername) {
        var rawList = catRepository.getAllByName(name);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    public List<Cat> getAllByBirthday(LocalDate birthday, String ownerUsername) {
        var rawList = catRepository.getAllByBirthday(birthday);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    public List<Cat> getAllByBreed(String breed, String ownerUsername) {
        var rawList = catRepository.getAllByBreed(breed);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    public List<Cat> getAllByColor(String color, String ownerUsername) {
        var rawList = catRepository.getAllByColor(Colors.valueOf(color));
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    public List<Cat> getAllByOwnerId(int id, String ownerUsername) {
        var rawList = catRepository.getAllByOwnerId(id);
        List<Cat> resultList = rawList.stream()
                .filter(x -> userCheckService.IsUserHaveAuthorityCatFilter(ownerUsername,x))
                .toList();
        if (resultList.isEmpty()) {
            throw UserException.ResultIsEmpty();
        }
        return resultList;
    }

    public void deleteAll(String ownerUsername) {
        Owner ownerOfUser = userCheckService.ownerOfUser(ownerUsername);
        var listCatForDelete = getAllByOwnerId(ownerOfUser.getId(), ownerUsername);
        listCatForDelete.forEach(x -> deleteCat(x.getId(), ownerUsername));
    }

    public List<Cat> getAll() {
        return catRepository.findAll();
    }

}