package az.scanerist.Scenarios.CancelOperation;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.IScenario;
import az.scanerist.Transactions.ITransaction;

import java.util.List;
import java.util.Scanner;

public class CancelOperationScenario implements IScenario {
    private CurrentClient _currentAccount;

    public CancelOperationScenario(CurrentClient currentAccount) {
        _currentAccount = currentAccount;
    }

    @Override
    public String GetName() {
        return "Cancel Operation";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<ICryptoBankAccount> accountList = _currentAccount.get_client().get_accounts();
        System.out.println("Choose account to cancel operation:");
        for (int i = 0; i < accountList.size(); i++) {
            System.out.println(i + ". " + accountList.get(i).GetId() + " " + accountList.get(i).GetAccountType() + " " + accountList.get(i).GetBalance() + " " + accountList.get(i).GetId());
        }
        int choice = scanner.nextInt();
        ICryptoBankAccount account = accountList.get(choice);
        System.out.println("Enter the operation id to cancel:");
        List<ITransaction> transactions = account.GetHistory();
        for(int i = 0; i < transactions.size(); i++) {
            System.out.println(i + ". " + transactions.get(i).GetId() + " " + transactions.get(i).GetTransferAmount() + " " + transactions.get(i).GetStatus());
        }
        int operationId = scanner.nextInt();
        ITransaction transaction = transactions.get(operationId);
        transaction.Undo();
    }
}
