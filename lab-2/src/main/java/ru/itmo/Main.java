package ru.itmo;


import ru.itmo.console.ScenarioRunner;


public class Main {
    public static void main(String[] args) {
        ScenarioRunner scenarioRunner = new ScenarioRunner(InitialProviders.getProviders());
        while (true) {
            scenarioRunner.run();
        }
    }
}