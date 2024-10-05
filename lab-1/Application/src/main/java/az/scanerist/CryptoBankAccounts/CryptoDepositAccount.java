package az.scanerist.CryptoBankAccounts;

import az.scanerist.Banks.IBank;
import az.scanerist.Clients.Client;
import az.scanerist.Clients.ClientStatus.DoubtfulStatus;
import az.scanerist.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import az.scanerist.ResultTypes.CancelTransactionResults.CancelTransactionSuccess;
import az.scanerist.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import az.scanerist.ResultTypes.TransferMoneyResults.TransferMoneySuccess;
import az.scanerist.ResultTypes.WithdrawMoneyResults.*;
import az.scanerist.Transactions.ITransaction;
import az.scanerist.Transactions.Transaction;
import az.scanerist.Transactions.TransactionStatus.CancelStatus;
import az.scanerist.Transactions.TransactionStatus.CompletedStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CryptoDepositAccount implements ICryptoBankAccount {
    private UUID _id;
    private List<ITransaction> _history;
    private Client _client;
    private Float _balance;
    private IBank _bank;
    private LocalDate _finalDate;

    public CryptoDepositAccount(Float balance, Client client, IBank bank, LocalDate finalDate) {
        _id = UUID.randomUUID();
        _history = new ArrayList<>();
        _balance = balance;
        _client = client;
        _bank = bank;
        _finalDate = finalDate;
    }

    @Override
    public String GetAccountType() {
        return "Deposit";
    }

    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Client GetClient() {
        return _client;
    }

    @Override
    public Float GetBalance() {
        return _balance;
    }

    @Override
    public List<ITransaction> GetHistory() {
        return _history;
    }

    @Override
    public WithdrawResult WithdrawMoney(Float money) {
        if (_finalDate.isAfter(LocalDate.now())) {
            return new WithdrawMoneyDepositLimit();
        }
        if (_balance < money) {
            return new NotEnoughMoneyResult();
        }
        if (_bank.GetMaxWithdraw() < money && _client.get_status() instanceof DoubtfulStatus) {
            return new WithdrawMoneyNotVerified();
        }
        var newTransaction = new Transaction(money, this, null);
        _history.add(newTransaction);
        _balance -= money;
        newTransaction.SetStatus(new CompletedStatus());
        return new WithdrawSuccessResult();
    }

    @Override
    public WithdrawResult WithdrawMoney(Float money, ICryptoBankAccount account) {
        ITransaction newTransaction = new Transaction(money, this, account);
        _history.add(newTransaction);
        _balance -= money;
        newTransaction.SetStatus(new CompletedStatus());
        return new WithdrawSuccessResult();
    }

    @Override
    public void Replenishment(Float money) {
        var newTransaction = new Transaction(money, null, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void Replenishment(Float money, ICryptoBankAccount account) {
        var newTransaction = new Transaction(money, account, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public TransferMoneyResult TransferMoney(ICryptoBankAccount toTranslation, Float money) {
        this.WithdrawMoney(money, toTranslation);
        toTranslation.Replenishment(money, this);
        return new TransferMoneySuccess();
    }

    @Override
    public CancelTransactionResult CancelTransaction(ITransaction toCancel) {
        if (toCancel.GetToTranslation() == this) {
            _balance -= toCancel.GetTransferAmount();
        }
        if (toCancel.GetTranslationSource() == this) {
            _balance += toCancel.GetTransferAmount();
        }

        toCancel.SetStatus(new CancelStatus());
        return new CancelTransactionSuccess();
    }

    @Override
    public void DoTransaction(ITransaction transaction) {
        _history.add(transaction);
        if (transaction.GetToTranslation() == this) {
            _balance += transaction.GetTransferAmount();
        }
        if (transaction.GetTranslationSource() == this) {
            _balance -= transaction.GetTransferAmount();
        }

        transaction.SetStatus(new CompletedStatus());
    }


    @Override
    public void Update(LocalDate time) {
        if (time.isBefore(_finalDate)) {
            for (var rate : _bank.GetDepositRates()) {
                if (rate.getLowerLimit() <= _balance && _balance <= rate.getUpperLimit()) {
                    _balance += _balance * rate.getInterest();
                }
            }
        }
    }
}
