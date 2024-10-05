package az.scanerist.Scenarios.ChooseBanks;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;
import az.scanerist.Repositories.BankRepository;

public class ChooseBankScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private BankRepository _bankRepository;

    public ChooseBankScenarioProvider(CurrentBank currentBank, BankRepository bankRepository) {
        _currentBank = currentBank;
        _bankRepository = bankRepository;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() == null) {
            return new ChooseBankScenario(_currentBank, _bankRepository);
        }
        return null;
    }
}
