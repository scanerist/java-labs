package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;

import java.time.LocalDate;
import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> getAllByOwnerId(Integer id);
    List<Cat> getAllByName(String name);
    List<Cat> getAllByBirthday(LocalDate birthday);
    List<Cat> getAllByBreed(String breed);
    List<Cat> getAllByColor(Colors colorType);
}
