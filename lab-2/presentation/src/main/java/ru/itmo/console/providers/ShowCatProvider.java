package ru.itmo.console.providers;

import ru.itmo.console.scenarios.Scenario;
import ru.itmo.console.scenarios.ShowCatsScenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.owners.CurrentOwnerService;

public class ShowCatProvider implements Provider{
    private CurrentOwnerService currentOwnerService;
    private CatService catService;
    public ShowCatProvider(CurrentOwnerService currentOwnerService, CatService catService) {
        this.currentOwnerService = currentOwnerService;
        this.catService = catService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentOwnerService.getOwner() != null) {
            return new ShowCatsScenario(currentOwnerService, catService);
        }
        return null;
    }
}
