package ru.itmo.console.providers;

import ru.itmo.console.scenarios.DeleteOwnerScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.owners.OwnerService;

import java.io.InputStream;

public class DeleteOwnerProvider implements Provider{
    private OwnerService ownerService;
    private InputStream inputStream;

    public DeleteOwnerProvider(OwnerService ownerService, InputStream inputStream) {
        this.ownerService = ownerService;
        this.inputStream = inputStream;
    }

    @Override
    public Scenario tryGetScenario() {
        return new DeleteOwnerScenario(ownerService, inputStream);
    }
}
