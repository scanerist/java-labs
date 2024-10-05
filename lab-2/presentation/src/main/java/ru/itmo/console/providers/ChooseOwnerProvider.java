package ru.itmo.console.providers;

import ru.itmo.console.scenarios.ChooseOwnerScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.contracts.owners.OwnerService;

public class ChooseOwnerProvider implements Provider {
    private CurrentOwnerService currentOwnerService;
    private OwnerService ownerService;

    public ChooseOwnerProvider(CurrentOwnerService currentOwnerService, OwnerService ownerService) {
        this.currentOwnerService = currentOwnerService;
        this.ownerService = ownerService;
    }

    @Override
    public Scenario tryGetScenario() {
        return new ChooseOwnerScenario(currentOwnerService, ownerService);
    }
}
