package az.scanerist.Banks;

import az.scanerist.Models.CryptoDepositRate;

import java.util.List;

public class CryptoBankBuilder {
    private String _name;
    private Float _debitInterest;
    private List<CryptoDepositRate> _depositRates;
    private Float _creditCommission;
    private Float _maxWithdraw;
    private Float _maxRemittance;

    public void AddDepositRate(CryptoDepositRate depositRate) {
        _depositRates.add(depositRate);
    }

    public void WithName(String name) {
        _name = name;
    }

    public void WithDebitInterest(Float debitInterest) {
        _debitInterest = debitInterest;
    }

    public void WithCreditCommission(Float creditCommission) {
        _creditCommission = creditCommission;
    }

    public void WithMaxWithdraw(Float maxWithdraw) {
        _maxWithdraw = maxWithdraw;
    }

    public void WithMaxRemittance(Float maxRemittance) {
        _maxRemittance = maxRemittance;
    }

    public IBank Build() {
        return new CryptoBank(_name, _debitInterest, _depositRates, _creditCommission, _maxWithdraw, _maxRemittance);
    }
}
