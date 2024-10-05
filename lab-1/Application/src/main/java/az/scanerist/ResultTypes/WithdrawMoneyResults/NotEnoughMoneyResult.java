package az.scanerist.ResultTypes.WithdrawMoneyResults;

public class NotEnoughMoneyResult implements WithdrawResult{
    @Override
    public String Message() {
        return "Not Enough Money";
    }
}
