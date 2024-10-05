package az.scanerist.Scenarios.CreateAccounts.CreateCreditAccount;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;

public class CreateCreditScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateCreditScenarioProvider(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentUser.get_client() == null || _currentBank.getBank() == null) {
            return null;
        }
        return new CreateCreditScenario(_currentBank, _currentUser);
    }
}
