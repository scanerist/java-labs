package az.scanerist.CurrentStates.CurrentBank;

import az.scanerist.Banks.IBank;

public class CurrentBank {
    private IBank bank;

    public IBank getBank() {
        return bank;
    }

    public void setBank(IBank bank) {
        this.bank = bank;
    }
}
