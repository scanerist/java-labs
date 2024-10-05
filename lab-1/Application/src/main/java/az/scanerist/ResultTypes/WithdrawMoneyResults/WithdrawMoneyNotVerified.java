package az.scanerist.ResultTypes.WithdrawMoneyResults;

public class WithdrawMoneyNotVerified implements WithdrawResult{
    @Override
    public String Message() {
        return "Account not verified!";
    }
}
