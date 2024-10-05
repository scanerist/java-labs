package ru.itmo.console.providers;

import ru.itmo.console.scenarios.Scenario;

public interface Provider {
    Scenario tryGetScenario();
}
