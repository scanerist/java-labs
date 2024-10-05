package ru.itmo.contracts.owners;

import ru.itmo.models.Owner;

public interface CurrentOwnerService {
    Owner getOwner();
    void setOwner(Owner owner);
}
