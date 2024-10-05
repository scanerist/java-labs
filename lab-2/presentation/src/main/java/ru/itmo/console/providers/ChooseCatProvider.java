package ru.itmo.console.providers;

import ru.itmo.console.scenarios.ChooseCatScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;

public class ChooseCatProvider implements Provider {
    private CatService catService;
    private CurrentCatService currentCatService;

    public ChooseCatProvider(CatService catService, CurrentCatService currentCatService) {
        this.catService = catService;
        this.currentCatService = currentCatService;
    }

    @Override
    public Scenario tryGetScenario() {
        return new ChooseCatScenario(catService, currentCatService);
    }
}
