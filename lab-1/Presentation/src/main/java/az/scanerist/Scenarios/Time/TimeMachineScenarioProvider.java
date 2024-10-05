package az.scanerist.Scenarios.Time;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;
import az.scanerist.Scenarios.ShowAccounts.ShowAccountsScenario;

public class TimeMachineScenarioProvider implements IScenarioProvider {
    private CurrentClient _currentClient;
    private CurrentBank _currentBank;

    public TimeMachineScenarioProvider(CurrentClient currentClient, CurrentBank currentBank) {
        _currentClient = currentClient;
        _currentBank = currentBank;
    }

    @Override
    public IScenario TryGetScenario() {
        IScenario scenario = null;
        if (_currentBank.getBank() != null && _currentClient.get_client() != null) {
            scenario = new ShowAccountsScenario(_currentBank, _currentClient);
        }
        return scenario;
    }
}
