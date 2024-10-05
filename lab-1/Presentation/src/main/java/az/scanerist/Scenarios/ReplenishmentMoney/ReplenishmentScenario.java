package az.scanerist.Scenarios.ReplenishmentMoney;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;

import java.util.List;
import java.util.Scanner;

public class ReplenishmentScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ReplenishmentScenario(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public String GetName() {
        return "Replenishment";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<ICryptoBankAccount> bankAccounts = _currentClient.get_client().get_accounts();
        System.out.println("Choose account to replenish: ");
        for (int i = 0; i < bankAccounts.size(); ++i) {
            ICryptoBankAccount bankAccount = bankAccounts.get(i);
            System.out.print(i + ". Type : " + bankAccount.GetAccountType() + "; ");
            System.out.print("Id: : " + bankAccount.GetId() + "; ");
            System.out.print("Balance : " + bankAccount.GetBalance() + "\n");
        }
        int choice = scanner.nextInt();
        ICryptoBankAccount account = bankAccounts.get(choice);
        System.out.println("Write amount to replenish: ");
        Float amount = scanner.nextFloat();
        account.Replenishment(amount);
    }
}
