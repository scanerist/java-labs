package ru.itmo.logic.owners;

import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.models.Owner;

public class CurrentOwnerServiceImpl implements CurrentOwnerService {
    private Owner owner;

    @Override
    public Owner getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
