package az.scanerist.Clients;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Banks.IBank;
import az.scanerist.Clients.ClientStatus.ClientStatus;
import az.scanerist.Clients.ClientStatus.DoubtfulStatus;
import az.scanerist.Clients.ClientStatus.VerifiedStatus;
import az.scanerist.Exceptions.ClientExceprions.InvalidCreationClient;
import az.scanerist.Models.CryptoUserAddress;
import az.scanerist.Models.Passport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Client {
    private final UUID _id;
    private final String _name;
    private final String _surname;
    private Passport _passport;
    private CryptoUserAddress _address;
    private List<ICryptoBankAccount> _accounts;
    private ClientStatus _status;

    private Client(String name, String surname, Passport passport, CryptoUserAddress address, ClientStatus status) {
        _id = UUID.randomUUID();
        _name = name;
        _surname = surname;
        _passport = passport;
        _address = address;
        _accounts = new ArrayList<>();
        _status = status;
    }

    public UUID get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_surname() {
        return _surname;
    }

    public Passport get_passport() {
        return _passport;
    }

    public CryptoUserAddress get_address() {
        return _address;
    }

    public List<ICryptoBankAccount> get_accounts() {
        return _accounts;
    }

    public ClientStatus get_status() {
        return _status;
    }

    public void AddAccount(ICryptoBankAccount bankAccount) {
        _accounts.add(bankAccount);
    }

    public void AddPassport(Passport passport) {
        _passport = passport;
        if (_address != null) {
            _status = new VerifiedStatus();
        }
    }

    public void AddAddress(CryptoUserAddress address) {
        _address = address;
        if (_passport != null) {
            _status = new VerifiedStatus();
        }
    }


    public static class ClientBuilder {
        private Optional<String> _name;
        private Optional<String> _surname;
        private Passport _passport;
        private CryptoUserAddress _address;
        private ClientStatus _status;
        private IBank _bank;

        public void AddName(String name) {
            _name = name.describeConstable();
        }

        public void AddSurname(String surname) {
            _surname = surname.describeConstable();
        }

        public void AddPassport(Passport passport) {
            _passport = passport;
        }

        public void AddAddress(CryptoUserAddress address) {
            _address = address;
        }
        public void AddBank(IBank bank) {
            _bank = bank;
        }

        public Client Build() {
            if (_name.isEmpty() || _surname.isEmpty()) {
                throw new InvalidCreationClient();
            }
            if (_passport == null || _address == null) {
                _status = new DoubtfulStatus();
            } else {
                _status = new VerifiedStatus();
            }
            return new Client(_name.get(), _surname.get(), _passport, _address, _status);
        }

    }
}
