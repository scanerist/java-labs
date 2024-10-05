package ru.itmo.console.providers;

import ru.itmo.console.scenarios.RemoveFriendScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;

import java.io.InputStream;

public class RemoveFriendProvider implements Provider{
    private CatService catService;
    private CurrentCatService currentCatService;
    private InputStream inputStream;

    public RemoveFriendProvider(CatService catService, CurrentCatService currentCatService, InputStream inputStream) {
        this.catService = catService;
        this.currentCatService = currentCatService;
        this.inputStream = inputStream;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentCatService.getCat() != null) {
            return new RemoveFriendScenario(catService, currentCatService, inputStream);
        }
        return null;
    }
}
