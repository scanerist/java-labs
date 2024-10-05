package az.scanerist.ResultTypes.WithdrawMoneyResults;

public class WithdrawSuccessResult implements WithdrawResult{
    @Override
    public String Message() {
        return "Success withdraw money!";
    }
}
