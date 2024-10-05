package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.models.Cat;

import java.util.List;

public class ShowCatsScenario implements Scenario {
    private CurrentOwnerService currentOwnerService;
    private CatService catService;

    public ShowCatsScenario(CurrentOwnerService currentOwnerService, CatService catService) {
        this.currentOwnerService = currentOwnerService;
        this.catService = catService;
    }

    @Override
    public String name() {
        return "Show cats";
    }

    @Override
    public void run() {
        var currentOwner = currentOwnerService.getOwner();
        List<Cat> cats = catService.getCatsByOwner(currentOwner);
        for(int i = 0; i < cats.size(); ++i) {
            System.out.println(i + ") " + cats.get(i));
        }
    }
}
