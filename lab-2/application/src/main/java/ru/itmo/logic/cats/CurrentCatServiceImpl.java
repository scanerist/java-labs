package ru.itmo.logic.cats;

import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.models.Cat;

public class CurrentCatServiceImpl implements CurrentCatService {
    private Cat cat;

    @Override
    public Cat getCat() {
        return cat;
    }

    @Override
    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
