package ru.itmo.console.providers;

import ru.itmo.console.scenarios.Scenario;
import ru.itmo.console.scenarios.ShowFriendsScenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;

public class ShowFriendsProvider implements Provider {
    private CurrentCatService currentCatService;
    private CatService catService;

    public ShowFriendsProvider(CurrentCatService currentCatService, CatService catService) {
        this.currentCatService = currentCatService;
        this.catService = catService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentCatService.getCat() != null) {
            return new ShowFriendsScenario(catService, currentCatService);
        }
        return null;
    }
}
