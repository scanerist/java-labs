package az.scanerist.Models;

public class CryptoDepositRate {
    private Float UpperLimit;
    private Float LowerLimit;
    private Float Interest;

    public CryptoDepositRate(Float lowerLimit, Float upperLimit, Float interest)
    {
        UpperLimit = upperLimit;
        LowerLimit = lowerLimit;
        Interest = interest;
    }


    public Float getUpperLimit() {
        return UpperLimit;
    }

    public Float getInterest() {
        return Interest;
    }

    public Float getLowerLimit() {
        return LowerLimit;
    }
}
