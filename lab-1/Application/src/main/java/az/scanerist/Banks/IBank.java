package az.scanerist.Banks;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Clients.Client;
import az.scanerist.Models.CryptoDepositRate;
import az.scanerist.Observer.ISubscriber;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IBank extends ISubscriber {
    String GetName();
    UUID GetId();
    Float GetDebitInterest();
    List<CryptoDepositRate> GetDepositRates();
    Float GetCreditCommission();
    Float GetMaxWithdraw();
    Float GetMaxRemittance();
    void AddBankAccount(ICryptoBankAccount bankAccount);
    ICryptoBankAccount CreateDebitAccount(Client client, Float startBalance);
    ICryptoBankAccount CreateCreditAccount(Client client, Float startBalance);
    ICryptoBankAccount CreateDepositAccount(Client client, Float startBalance, LocalDate finalDate);
    List<ICryptoBankAccount> GetBankAccounts();

}
