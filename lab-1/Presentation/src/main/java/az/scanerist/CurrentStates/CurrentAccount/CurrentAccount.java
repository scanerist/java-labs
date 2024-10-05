package az.scanerist.CurrentStates.CurrentAccount;

import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;

public class CurrentAccount {
    private ICryptoBankAccount _account;

    public ICryptoBankAccount get_account() {
        return _account;
    }

    public void set_account(ICryptoBankAccount _account) {
        this._account = _account;
    }
}
