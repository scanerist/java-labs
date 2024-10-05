package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.CatColour;
import ru.itmo.models.Cat;

import java.util.List;

@Repository
public interface CatRepositoryJpa extends JpaRepository<Cat, Integer> {

    List<Cat> findCatsByOwner(String ownerName);

    List<Cat> findByOwnerAndName(String ownerName, String catName);

    List<Cat> findCatsByColour(CatColour colour);

    List<Cat> findCatsByBreed(String breed);

    List<Cat> findCatsByColourAndOwner(CatColour colour, String ownerName);

    List<Cat> findCatsByBreedAndOwner(String breed, String ownerName);
}
