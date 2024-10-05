package ru.itmo.console.providers;

import ru.itmo.console.scenarios.AddCatScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.owners.CurrentOwnerService;

public class AddCatProvider implements Provider {
    private CatService catService;
    private CurrentOwnerService currentOwnerService;
    public AddCatProvider(CatService catService, CurrentOwnerService currentOwnerService) {
        this.catService = catService;
        this.currentOwnerService = currentOwnerService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentOwnerService.getOwner() != null) {
            return new AddCatScenario(catService, currentOwnerService);
        }
        return null;
    }
}
