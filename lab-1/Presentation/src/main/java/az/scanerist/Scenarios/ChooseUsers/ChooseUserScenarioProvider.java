package az.scanerist.Scenarios.ChooseUsers;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.IScenarioProvider;
import az.scanerist.Repositories.CryptoClientRepository;

public class ChooseUserScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;
    private CryptoClientRepository _clientRepository;

    public ChooseUserScenarioProvider(CurrentBank currentBank, CurrentClient currentClient, CryptoClientRepository clientRepository) {
        _currentBank = currentBank;
        _currentClient = currentClient;
        _clientRepository = clientRepository;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() != null && _currentClient.get_client() == null) {
            return new ChooseUserScenario(_currentBank, _currentClient, _clientRepository);
        }
        return null;
    }
}
