package az.scanerist.Scenarios.ShowAccounts;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;

import java.util.List;

public class ShowAccountsScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ShowAccountsScenario(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public String GetName() {
        return "Show Accounts";
    }

    @Override
    public void Run() {
        List<ICryptoBankAccount> accounts = _currentClient.get_client().get_accounts();
        for (int i = 0; i < accounts.size(); ++i) {
            ICryptoBankAccount bankAccount = accounts.get(i);
            System.out.print("Type : " + bankAccount.getClass() + "\n");
            System.out.print("BankId : " + bankAccount.GetId() + "\n");
            System.out.print("Balance : " + bankAccount.GetBalance() + "\n");
        }
    }
}
