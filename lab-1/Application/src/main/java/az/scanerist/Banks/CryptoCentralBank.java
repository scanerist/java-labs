package az.scanerist.Banks;

import az.scanerist.Models.CryptoDepositRate;
import az.scanerist.Observer.ISubscriber;

import java.time.LocalDate;
import java.util.*;

public class CryptoCentralBank {
    private static CryptoCentralBank instance;
    private final List<IBank> _banks;
    private final List<ISubscriber> _subscribers;

    private CryptoCentralBank() {
        _subscribers = new ArrayList<>();
        _banks = new ArrayList<>();
    }

    public static CryptoCentralBank getInstance() {
        if (instance == null) {
            instance = new CryptoCentralBank();
        }
        return instance;
    }

    public List<IBank> GetBanks()
    {
        return Collections.unmodifiableList(_banks);
    }

    public IBank CreateBank(String name, Float debitInterest, List<CryptoDepositRate> depositRates, Float creditCommission, Float maxWithdraw, Float maxRemittance) {
        IBank bank = new CryptoBank(name, debitInterest, depositRates, creditCommission, maxWithdraw, maxRemittance);
        _banks.add(bank);
        _subscribers.add(bank);
        return bank;
    }

    public IBank GetBankByName(String bankName) {
        if (bankName == null) {
            throw new IllegalArgumentException();
        }

        for (IBank bank : _banks) {
            if (bank.GetName().equals(bankName)) {
                return bank;
            }
        }

        throw new IllegalArgumentException();
    }

    public IBank GetBankById(UUID id) {
        if (id == null) {
            throw new NullPointerException();
        }

        for (IBank bankImpl : _banks) {
            if (bankImpl.GetId().equals(id)) {
                return bankImpl;
            }
        }

        throw new IllegalArgumentException();
    }

    public void AddSubscriber(ISubscriber subscriber) {
        _subscribers.add(subscriber);
    }

    public void RemoveSubscriber(ISubscriber subscriber) {
        _subscribers.remove(subscriber);
    }

    public void NotifySubscribers(LocalDate time) {
        for(IBank bank : _banks) {
            bank.Update(time);
        }
    }
}
