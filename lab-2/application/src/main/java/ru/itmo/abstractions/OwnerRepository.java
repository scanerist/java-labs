package ru.itmo.abstractions;

import ru.itmo.models.Owner;

import java.util.List;

public interface OwnerRepository
{
    List<Owner> index();
    Owner getById(int id);
    Owner getByName(String name);
    void save(Owner owner);
    void delete(Owner owner);
}
