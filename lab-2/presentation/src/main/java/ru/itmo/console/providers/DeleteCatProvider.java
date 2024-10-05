package ru.itmo.console.providers;

import ru.itmo.console.scenarios.DeleteCatScenario;
import ru.itmo.console.scenarios.Scenario;
import ru.itmo.contracts.cats.CatService;

import java.io.InputStream;
import java.util.Scanner;

public class DeleteCatProvider implements Provider {
    private CatService catService;
    private InputStream inputStream;


    public DeleteCatProvider(CatService catService, InputStream inputStream) {
        this.catService = catService;
        this.inputStream = inputStream;
    }

    @Override
    public Scenario tryGetScenario() {
        return new DeleteCatScenario(catService, inputStream);
    }
}
