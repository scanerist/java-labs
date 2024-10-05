package ru.itmo.contracts.owners;

import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    List<Owner> getAllOwners();
    Owner addOwner(String name, LocalDate birthDate);
    Owner getOwnerById(int id);
    Owner getOwnerByName(String name);
    void deleteOwner(Owner owner);
}
