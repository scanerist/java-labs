package az.scanerist.Scenarios.CreateAccounts.CreateCreditAccount;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;

import java.util.Scanner;

public class CreateCreditScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateCreditScenario(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Create Credit Account";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write start balance: ");
        Float startBalance = scanner.nextFloat();
        _currentBank.getBank().CreateCreditAccount(_currentUser.get_client(), startBalance);
    }
}
