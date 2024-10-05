package az.scanerist.Scenarios.ShowAccounts;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;

public class ShowAccountsScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ShowAccountsScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
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
