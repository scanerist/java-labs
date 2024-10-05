package az.scanerist.Banks;

import az.scanerist.CryptoBankAccounts.CryptoAccount;
import az.scanerist.CryptoBankAccounts.CryptoDebitAccount;
import az.scanerist.CryptoBankAccounts.CryptoDepositAccount;
import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Clients.Client;
import az.scanerist.Models.CryptoDepositRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CryptoBank implements IBank{
    private UUID _id;
    private String _name;
    private List<Client> _clients;
    private List<ICryptoBankAccount> _accounts;
    private Float _debitInterest;
    private List<CryptoDepositRate> _depositRates;
    private Float _creditCommission;
    private Float _maxWithdraw;
    private Float _maxRemittance;

    public CryptoBank(String name, Float debitInterest, List<CryptoDepositRate> depositRates, Float creditCommission, Float maxWithdraw, Float maxRemittance) {
        _id = UUID.randomUUID();
        _name = name;
        _debitInterest = debitInterest;
        _depositRates = depositRates;
        _creditCommission = creditCommission;
        _accounts = new ArrayList<>();
        _maxWithdraw = maxWithdraw;
        _maxRemittance = maxRemittance;
    }


    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Float GetDebitInterest() {
        return _debitInterest;
    }

    @Override
    public List<CryptoDepositRate> GetDepositRates() {
        return _depositRates;
    }

    @Override
    public Float GetCreditCommission() {
        return _creditCommission;
    }

    @Override
    public Float GetMaxWithdraw() {
        return _maxWithdraw;
    }

    @Override
    public Float GetMaxRemittance() {
        return _maxRemittance;
    }


    @Override
    public void AddBankAccount(ICryptoBankAccount bankAccount) {
        _accounts.add(bankAccount);
    }

    @Override
    public ICryptoBankAccount CreateDebitAccount(Client client, Float startBalance) {
        ICryptoBankAccount account = new CryptoDebitAccount(startBalance, client, this);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public ICryptoBankAccount CreateCreditAccount(Client client, Float startBalance) {
        ICryptoBankAccount account = new CryptoAccount(startBalance, client, this);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public ICryptoBankAccount CreateDepositAccount(Client client, Float startBalance, LocalDate finalDate) {
        ICryptoBankAccount account = new CryptoDepositAccount(startBalance, client, this, finalDate);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public List<ICryptoBankAccount> GetBankAccounts() {
        return _accounts;
    }

    @Override
    public void Update(LocalDate time) {
        for(ICryptoBankAccount subscriber : _accounts) {
            subscriber.Update(time);
        }
    }
}
