package ru.itmo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.dto.UserDto;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepository;
import ru.itmo.repositories.OwnerRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OwnerService  {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CatRepository catRepository;

    public Owner addOwner(UserDto userDto) {
        Owner owner = new Owner(userDto.getUsername(), LocalDate.parse(userDto.getBirthdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return ownerRepository.save(owner);
    }

    public void deleteOwner(int id) {
        ownerRepository.deleteById(id);
    }

    public void addCatToOwner(int catId, int ownerId) {
        Cat cat = catRepository.getReferenceById(catId);
        Owner owner = ownerRepository.getReferenceById(ownerId);
        owner.addCat(cat);
    }


    public Owner getOwnerById(int id) {
        return ownerRepository.getReferenceById(id);
    }


    public List<Owner> getAllByName(String name) {
        return ownerRepository.getAllByName(name);
    }

    public List<Owner> getAllByBirthday(LocalDate birthday) {
        return ownerRepository.getAllByBirthday(birthday);
    }

    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }
}