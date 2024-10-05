package az.scanerist.Transactions;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Transactions.TransactionStatus.TransactionStatus;

import java.util.UUID;

public class Transaction implements ITransaction{
    private UUID _id;
    private Float _transferAmount;
    private ICryptoBankAccount _translationSource;
    private ICryptoBankAccount _toTranslation;
    private TransactionStatus _status;

    public Transaction(Float transferAmount, ICryptoBankAccount translationSource, ICryptoBankAccount toTranslation) {
        _id = UUID.randomUUID();
        _transferAmount = transferAmount;
        _translationSource = translationSource;
        _toTranslation = toTranslation;
    }


    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Float GetTransferAmount() {
        return _transferAmount;
    }

    @Override
    public ICryptoBankAccount GetTranslationSource() {
        return _translationSource;
    }

    @Override
    public ICryptoBankAccount GetToTranslation() {
        return _toTranslation;
    }

    @Override
    public TransactionStatus GetStatus() {
        return _status;
    }

    @Override
    public void SetStatus(TransactionStatus status) {
        _status = status;
    }

    @Override
    public void Execute() {
        _translationSource.DoTransaction(this);
        _toTranslation.DoTransaction(this);
    }

    @Override
    public void Undo() {
        _translationSource.CancelTransaction(this);
        _toTranslation.CancelTransaction(this);

    }
}
