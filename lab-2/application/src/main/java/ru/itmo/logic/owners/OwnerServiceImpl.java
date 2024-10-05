package ru.itmo.logic.owners;

import ru.itmo.abstractions.CatRepository;
import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.contracts.owners.OwnerService;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.index();
    }

    @Override
    public Owner addOwner(String name, LocalDate birthDate) {
        Owner owner = new Owner(name, birthDate);
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner getOwnerById(int id) {
        return ownerRepository.getById(id);
    }

    @Override
    public Owner getOwnerByName(String name) {
        return ownerRepository.getByName(name);
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }
}
