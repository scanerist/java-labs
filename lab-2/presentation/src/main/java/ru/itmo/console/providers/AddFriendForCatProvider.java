package ru.itmo.console.providers;

import ru.itmo.console.scenarios.AddFriendForCatScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;

public class AddFriendForCatProvider implements Provider {
    private CurrentCatService currentCatService;
    private CatService catService;

    public AddFriendForCatProvider(CurrentCatService currentCatService, CatService catService) {
        this.currentCatService = currentCatService;
        this.catService = catService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentCatService.getCat() != null) {
            return new AddFriendForCatScenario(currentCatService, catService);
        }
        return null;
    }
}
