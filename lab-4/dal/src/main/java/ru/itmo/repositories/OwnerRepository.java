package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findDistinctByName (String name);
    List<Owner> getAllByName(String name);
    List<Owner> getAllByBirthday(LocalDate date);
}