package ru.itmo;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepository;
import ru.itmo.repositories.OwnerRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Transactional(readOnly = true)
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Owner findById(int id) {
        Optional<Owner> foundOwner = ownerRepository.findById(id);
        return foundOwner.orElse(null);
    }

    @Transactional(readOnly = true)
    public Owner getByName(String name) {
        return ownerRepository.findByName(name).orElse(null);
    }

    @Transactional
    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    @Transactional
    public void delete(int id) {
        ownerRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<Cat> getCatsByOwnerId(int id) {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (owner.isPresent()) {
            Hibernate.initialize(owner.get().getCats());
            return owner.get().getCats();
        } else {
            return Collections.emptyList();
        }
    }
}