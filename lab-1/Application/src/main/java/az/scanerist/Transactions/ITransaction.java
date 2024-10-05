package az.scanerist.Transactions;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Transactions.TransactionStatus.TransactionStatus;

import java.util.UUID;

public interface ITransaction {
    UUID GetId();
    Float GetTransferAmount();
    ICryptoBankAccount GetTranslationSource();
    ICryptoBankAccount GetToTranslation();
    TransactionStatus GetStatus();
    void SetStatus(TransactionStatus status);
    void Execute();
    void Undo();
}
