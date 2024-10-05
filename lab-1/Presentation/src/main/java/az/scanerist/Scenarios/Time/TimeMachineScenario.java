package az.scanerist.Scenarios.Time;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Banks.CryptoCentralBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.TimeMachine.TimeMachine;

import java.util.List;
import java.util.Scanner;

public class TimeMachineScenario implements IScenario {
    private CurrentClient _currentClient;

    public TimeMachineScenario(CurrentClient currentClient) {
        _currentClient = currentClient;
    }

    @Override
    public String GetName() {
        return "Time Machine";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<ICryptoBankAccount> accounts = _currentClient.get_client().get_accounts();
        System.out.println("Choose account to transfer money from:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + ". " + accounts.get(i).GetId() + " " + accounts.get(i).GetAccountType() + " " + accounts.get(i).GetBalance() + " " + accounts.get(i).GetId());
        }
        int choice = scanner.nextInt();
        ICryptoBankAccount account = accounts.get(choice);

        System.out.println("Enter future date: ");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int days = scanner.nextInt();
        CryptoCentralBank centralBank = CryptoCentralBank.getInstance();
        TimeMachine timeMachine = new TimeMachine(centralBank);
        timeMachine.RewindTime(year, month, days);
        System.out.println(account.GetBalance());
    }
}
