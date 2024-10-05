package az.scanerist.Scenarios.TransferMoney;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;

public class TransferMoneyScenarioProvider implements IScenarioProvider {
    private CurrentClient _currentClient;
    private CurrentBank _currentBank;

    public TransferMoneyScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentClient = currentClient;
        _currentBank = currentBank;
    }


    @Override
    public IScenario TryGetScenario() {
        if(_currentClient.get_client() != null && _currentBank.getBank() != null) {
            return new TransferMoneyScenario(_currentClient, _currentBank);
        }
        return null;
    }
}
