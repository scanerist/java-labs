package az.scanerist.ResultTypes.WithdrawMoneyResults;

public class WithdrawMoneyDepositLimit implements WithdrawResult{
    @Override
    public String Message() {
        return "Not withdrawal time!";
    }
}
