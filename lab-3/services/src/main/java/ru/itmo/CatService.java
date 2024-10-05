package ru.itmo;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    private CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Transactional(readOnly = true)
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cat findById(int id) {
        Optional<Cat> cat = catRepository.findById(id);
        return cat.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cat> findByColor(Colors color) {
        Optional<List<Cat>> cats = catRepository.findCatsByColor(color);
        return cats.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cat> findByBreed(String breed) {
        Optional<List<Cat>> cats = catRepository.findCatsByBreed(breed);
        return cats.orElse(null);
    }

    @Transactional(readOnly = true)
    public Cat findByName(String name) {
        return catRepository.findCatByName(name).orElse(null);
    }

    @Transactional
    public void save(Cat cat) {
        catRepository.save(cat);
    }

    @Transactional
    public void delete(int id) {
        catRepository.deleteById(id);
    }

    public Owner getCatOwner(int id) {
        return catRepository.findById(id).map(Cat::getOwner).orElse(null);
    }
    public List<Cat> getCatFriends(int id) {
        Optional<Cat> cat = catRepository.findById(id);

        if (cat.isPresent()) {
            Hibernate.initialize(cat.get().getFriends());
            return cat.get().getFriends();
        } else {
            return Collections.emptyList();
        }
    }
}
