package ru.itmo.console;

import ru.itmo.console.providers.Provider;
import ru.itmo.console.scenarios.Scenario;

import java.util.List;
import java.util.Scanner;

public class ScenarioRunner {
    Scanner scanner;
    List<Provider> providers;
    public ScenarioRunner(List<Provider> providers) {
        this.providers = providers;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Choose scenario:");

        List<Scenario> scenarios = getScenarios();
        for (int i = 0; i < scenarios.size(); i++) {
            System.out.println(i + ") " + scenarios.get(i).name());
        }
        int scenarioIndex = scanner.nextInt();
        scenarios.get(scenarioIndex).run();
    }
    private List<Scenario> getScenarios() {
        return providers.stream()
                .map(Provider::tryGetScenario)
                .filter(scenario -> scenario != null)
                .toList();
    }
}
