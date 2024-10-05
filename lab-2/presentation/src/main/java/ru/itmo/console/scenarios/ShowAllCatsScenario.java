package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.models.Cat;

import java.util.List;

public class ShowAllCatsScenario implements Scenario{
    private CatService catService;

    public ShowAllCatsScenario(CatService catService) {
        this.catService = catService;
    }

    @Override
    public String name() {
        return "Show all cats";
    }

    @Override
    public void run() {
        List<Cat> cats = catService.getAllCats();
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }
}
