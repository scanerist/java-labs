import az.scanerist.CryptoBankAccounts.ICryptoBankAccount;
import az.scanerist.Banks.CryptoCentralBank;
import az.scanerist.Banks.IBank;
import az.scanerist.Clients.Client;
import az.scanerist.Models.CryptoUserAddress;
import az.scanerist.Models.CryptoDepositRate;
import az.scanerist.Models.Passport;
import az.scanerist.ResultTypes.TransferMoneyResults.TransferMoneyDoubtfulResult;
import az.scanerist.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import az.scanerist.ResultTypes.WithdrawMoneyResults.WithdrawMoneyNotVerified;
import az.scanerist.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import az.scanerist.TimeMachine.TimeMachine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private final CryptoCentralBank centralBank = CryptoCentralBank.getInstance();
    private final TimeMachine timeMachine = new TimeMachine(centralBank);
    @Test
    public void TransferMoneyFromMeToMyFriend() {
        List<CryptoDepositRate> depositRates = new ArrayList<>();
        depositRates.add(new CryptoDepositRate(0f, 50000f, 3f));
        depositRates.add(new CryptoDepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new CryptoDepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("nate");
        clientBuilder.AddSurname("higgg");
        clientBuilder.AddPassport(new Passport(1234, 56789));
        clientBuilder.AddAddress(new CryptoUserAddress("paris", 4));
        Client I = clientBuilder.Build();

        clientBuilder.AddName("kanye");
        clientBuilder.AddSurname("west");
        clientBuilder.AddPassport(new Passport(4321, 98765));
        Client MyFriend = clientBuilder.Build();

        ICryptoBankAccount IDebit = sber.CreateDebitAccount(I, 1000f);
        ICryptoBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1000f);

        IDebit.TransferMoney(MyFriendDebit, 200f);

        assertEquals(800, IDebit.GetBalance());
        assertEquals(1200, MyFriendDebit.GetBalance());
    }

    @Test
    public void TransferMoneyFromMeToMyFriendWithoutVerified() {
        List<CryptoDepositRate> depositRates = new ArrayList<>();
        depositRates.add(new CryptoDepositRate(0f, 50000f, 3f));
        depositRates.add(new CryptoDepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new CryptoDepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "pdiddy",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("nate");
        clientBuilder.AddSurname("higg");
        Client I = clientBuilder.Build();

        clientBuilder.AddName("kanye");
        clientBuilder.AddSurname("west");
        Client MyFriend = clientBuilder.Build();

        ICryptoBankAccount IDebit = sber.CreateDebitAccount(I, 1e6f);
        ICryptoBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1e6f);

        TransferMoneyResult result = IDebit.TransferMoney(MyFriendDebit, 1e5f);

        assertEquals(TransferMoneyDoubtfulResult.class, result.getClass());
    }

    @Test
    public void WithdrawMoneyFromNotVerifiedAccount() {
        List<CryptoDepositRate> depositRates = new ArrayList<>();
        depositRates.add(new CryptoDepositRate(0f, 50000f, 3f));
        depositRates.add(new CryptoDepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new CryptoDepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("nate");
        clientBuilder.AddSurname("higg");
        Client I = clientBuilder.Build();

        ICryptoBankAccount MyDebitAccount = sber.CreateDebitAccount(I, 1e6f);
        ICryptoBankAccount MyCreditAccount = sber.CreateCreditAccount(I, 1e6f);
        ICryptoBankAccount MyDepositAccount = sber.CreateDepositAccount(I, 1e6f, LocalDate.of(2024, 2, 29));

        WithdrawResult withdrawResult1 = MyDebitAccount.WithdrawMoney(1e5f);
        WithdrawResult withdrawResult2 = MyCreditAccount.WithdrawMoney(1e5f);
        WithdrawResult withdrawResult3 = MyDepositAccount.WithdrawMoney(1e5f);

        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult1.getClass());
        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult2.getClass());
        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult3.getClass());
    }

    @Test
    public void CancelOperation() {
        List<CryptoDepositRate> depositRates = new ArrayList<>();
        depositRates.add(new CryptoDepositRate(0f, 50000f, 3f));
        depositRates.add(new CryptoDepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new CryptoDepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("nate");
        clientBuilder.AddSurname("higg");
        clientBuilder.AddPassport(new Passport(1234, 56789));
        clientBuilder.AddAddress(new CryptoUserAddress("paris", 4));
        Client I = clientBuilder.Build();

        clientBuilder.AddName("kanye");
        clientBuilder.AddSurname("west");
        clientBuilder.AddPassport(new Passport(4321, 98765));
        Client MyFriend = clientBuilder.Build();

        ICryptoBankAccount IDebit = sber.CreateDebitAccount(I, 1000f);
        ICryptoBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1000f);

        ICryptoBankAccount MyCredit = sber.CreateCreditAccount(I, 1000f);
        ICryptoBankAccount MyFriendCredit = sber.CreateCreditAccount(MyFriend, 1000f);

        ICryptoBankAccount MyDeposit = sber.CreateDepositAccount(I, 1000f, LocalDate.of(2024, 2, 29));
        ICryptoBankAccount MyFriendDeposit = sber.CreateDepositAccount(MyFriend, 1000f, LocalDate.of(2024, 2, 29));

        IDebit.TransferMoney(MyFriendDebit, 200f);
        MyCredit.TransferMoney(MyFriendCredit, 200f);
        MyDeposit.TransferMoney(MyFriendDeposit, 200f);

        IDebit.GetHistory().get(0).Undo();
        MyCredit.GetHistory().get(0).Undo();
        MyDeposit.GetHistory().get(0).Undo();

        assertEquals(1000, MyFriendDebit.GetBalance());
        assertEquals(1000, IDebit.GetBalance());
        assertEquals(1000, MyCredit.GetBalance());
        assertEquals(1000, MyFriendCredit.GetBalance());
        assertEquals(1000, MyDeposit.GetBalance());
        assertEquals(1000, MyFriendDeposit.GetBalance());
    }

    @Test
    public void Time() {
        List<CryptoDepositRate> depositRates = new ArrayList<>();
        depositRates.add(new CryptoDepositRate(0f, 50000f, 3f));
        depositRates.add(new CryptoDepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new CryptoDepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("nate");
        clientBuilder.AddSurname("higg");
        clientBuilder.AddPassport(new Passport(1234, 56789));
        clientBuilder.AddAddress(new CryptoUserAddress("paris", 4));
        Client I = clientBuilder.Build();
        ICryptoBankAccount IDebit = sber.CreateDebitAccount(I, 1000f);
        timeMachine.RewindTime(2024, 4, 2);
        assertEquals(IDebit.GetBalance(), 49000f);
    }
}