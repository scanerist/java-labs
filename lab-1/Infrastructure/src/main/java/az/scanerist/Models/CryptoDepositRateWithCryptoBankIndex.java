package az.scanerist.Models;

public class CryptoDepositRateWithCryptoBankIndex {
    private CryptoDepositRate _depositRate;
    private Integer _bankIndex;

    public CryptoDepositRateWithCryptoBankIndex(CryptoDepositRate depositRate, Integer bankIndex) {
        _depositRate = depositRate;
        _bankIndex = bankIndex;
    }

    public CryptoDepositRate getDepositRate() {
        return _depositRate;
    }

    public Integer getBankIndex() {
        return _bankIndex;
    }
}
