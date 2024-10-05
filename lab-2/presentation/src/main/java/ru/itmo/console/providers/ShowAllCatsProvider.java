package ru.itmo.console.providers;

import ru.itmo.console.scenarios.Scenario;
import ru.itmo.console.scenarios.ShowAllCatsScenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.contracts.owners.CurrentOwnerService;

public class ShowAllCatsProvider implements Provider {
    private CurrentOwnerService currentOwnerService;
    private CurrentCatService currentCatService;
    private CatService catService;

    public ShowAllCatsProvider(CurrentOwnerService currentOwnerService, CurrentCatService currentCatService, CatService catService) {
        this.currentOwnerService = currentOwnerService;
        this.currentCatService = currentCatService;
        this.catService = catService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentOwnerService.getOwner() == null && currentCatService.getCat() == null) {
            return new ShowAllCatsScenario(catService);
        }
        return null;
    }
}
