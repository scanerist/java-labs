package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itmo.models.CatOwner;

@Repository
public interface CatOwnerRepositoryJpa extends JpaRepository<CatOwner, String> {

    @Query(value = "SELECT o FROM CatOwner o WHERE :id IN o.ownedCats", nativeQuery = true)
    CatOwner getByOwnedCatId(@Param("id") int id);
}
