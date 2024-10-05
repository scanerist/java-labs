package ru.itmo.contracts.cats;

import ru.itmo.models.Cat;

public interface CurrentCatService {
    Cat getCat();
    void setCat(Cat cat);
}
