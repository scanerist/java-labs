package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.entities.User;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Integer> {

    User getByOwner(String name);
}
