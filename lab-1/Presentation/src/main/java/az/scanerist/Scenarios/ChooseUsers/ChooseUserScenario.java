package az.scanerist.Scenarios.ChooseUsers;

import az.scanerist.Clients.Client;
import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.Repositories.CryptoClientRepository;

import java.util.List;
import java.util.Scanner;

public class ChooseUserScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;
    private CryptoClientRepository _clientRepository;

    public ChooseUserScenario(CurrentBank currentBank, CurrentClient currentClient, CryptoClientRepository clientRepository) {
        _currentBank = currentBank;
        _currentClient = currentClient;
        _clientRepository = clientRepository;
    }

    @Override
    public String GetName() {
        return "Choose Client";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = _clientRepository.getAllClients();
        System.out.println("Choose client: ");
        for (int i = 0; i < clients.size(); ++i) {
            System.out.println(i + ". " + clients.get(i).get_name());
        }
        int clientIndex = scanner.nextInt();
        _currentClient.set_client(clients.get(clientIndex));
    }
}
