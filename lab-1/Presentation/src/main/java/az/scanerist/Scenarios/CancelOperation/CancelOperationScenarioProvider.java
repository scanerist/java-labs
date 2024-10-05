package az.scanerist.Scenarios.CancelOperation;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;

public class CancelOperationScenarioProvider implements IScenarioProvider  {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public CancelOperationScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() != null && _currentClient.get_client() != null) {
            return new CancelOperationScenario(_currentClient);
        }
        return null;
    }
}
