package az.scanerist.CryptoBankAccounts;

import az.scanerist.Clients.Client;
import az.scanerist.Observer.ISubscriber;
import az.scanerist.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import az.scanerist.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import az.scanerist.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import az.scanerist.Transactions.ITransaction;

import java.util.List;
import java.util.UUID;

public interface ICryptoBankAccount extends ISubscriber {
    String GetAccountType();
    UUID GetId();
    Client GetClient();
    Float GetBalance();
    List<ITransaction> GetHistory();
    WithdrawResult WithdrawMoney(Float money);
    WithdrawResult WithdrawMoney(Float money, ICryptoBankAccount account);
    void Replenishment(Float money);
    void Replenishment(Float money, ICryptoBankAccount account);
    TransferMoneyResult TransferMoney(ICryptoBankAccount toTranslation, Float money);
    CancelTransactionResult CancelTransaction(ITransaction toCancel);
    void DoTransaction(ITransaction transaction);
}
