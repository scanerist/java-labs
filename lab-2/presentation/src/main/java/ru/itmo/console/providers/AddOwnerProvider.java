package ru.itmo.console.providers;

import ru.itmo.console.scenarios.AddOwnerScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.owners.OwnerService;

import java.io.InputStream;

public class AddOwnerProvider implements Provider {
    private OwnerService ownerService;
    private InputStream source;

    public AddOwnerProvider(OwnerService ownerService, InputStream source) {
        this.ownerService = ownerService;
        this.source = source;
    }


    @Override
    public Scenario tryGetScenario() {
        return new AddOwnerScenario(ownerService, source);
    }
}
