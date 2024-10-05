package az.scanerist.Scenarios.CreateAccounts.CreateDepositAccount;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;

import java.time.LocalDate;
import java.util.Scanner;

public class CreateDepositScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateDepositScenario(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Create Deposit Account";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write start balance: ");
        Float startBalance = scanner.nextFloat();
        _currentBank.getBank().CreateDepositAccount(_currentUser.get_client(), startBalance, LocalDate.now());
    }

}
