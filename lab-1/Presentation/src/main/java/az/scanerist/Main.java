package az.scanerist;

import az.scanerist.CurrentStates.CurrentBank.CurrentBank;
import az.scanerist.CurrentStates.CurrentClient.CurrentClient;
import az.scanerist.Repositories.BankRepository;
import az.scanerist.Repositories.CryptoClientRepository;
import az.scanerist.Scenarios.CancelOperation.CancelOperationScenarioProvider;
import az.scanerist.Scenarios.ChooseBanks.ChooseBankScenarioProvider;
import az.scanerist.Scenarios.ChooseUsers.ChooseUserScenarioProvider;
import az.scanerist.Scenarios.CreateAccounts.CreateCreditAccount.CreateCreditScenarioProvider;
import az.scanerist.Scenarios.CreateAccounts.CreateDebitAccount.CreateDebitScenarioProvider;
import az.scanerist.Scenarios.CreateAccounts.CreateDepositAccount.CreateDepositScenarioProvider;
import az.scanerist.Scenarios.CreateBanks.CreateBankScenarioProvider;
import az.scanerist.Scenarios.CreateClients.CreateClientScenarioProvider;
import az.scanerist.Scenarios.ReplenishmentMoney.ReplenishmentScenarioProvider;
import az.scanerist.Scenarios.ShowAccounts.ShowAccountsScenarioProvider;
import az.scanerist.Scenarios.Time.TimeMachineScenarioProvider;
import az.scanerist.Scenarios.TransferMoney.TransferMoneyScenarioProvider;
import az.scanerist.Scenarios.WithdrawMoney.WithdrawScenarioProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class
 */

public class Main {
    public static void main(String[] args) {
        CurrentBank currentBank = new CurrentBank();
        CurrentClient currentClient = new CurrentClient();
        BankRepository bankRepository = new BankRepository();
        CryptoClientRepository clientRepository = new CryptoClientRepository();
        List<IScenarioProvider> providers = new ArrayList<>();

        providers.add(new CreateBankScenarioProvider(currentBank, currentClient));
        providers.add(new CreateClientScenarioProvider(currentBank, currentClient));

        providers.add(new CreateDepositScenarioProvider(currentBank, currentClient));
        providers.add(new CreateDebitScenarioProvider(currentBank, currentClient));
        providers.add(new CreateCreditScenarioProvider(currentBank, currentClient));

        providers.add(new ShowAccountsScenarioProvider(currentBank, currentClient));

        providers.add(new WithdrawScenarioProvider(currentBank, currentClient));
        providers.add(new ReplenishmentScenarioProvider(currentBank, currentClient));
        providers.add(new TransferMoneyScenarioProvider(currentBank, currentClient));
        providers.add(new CancelOperationScenarioProvider(currentBank, currentClient));

        providers.add(new ChooseBankScenarioProvider(currentBank, bankRepository));
        providers.add(new ChooseUserScenarioProvider(currentBank, currentClient, clientRepository));

        providers.add(new TimeMachineScenarioProvider(currentClient, currentBank));


        ScenarioRunner runner = new ScenarioRunner(providers);
        runner.run();
        while (true) {
            runner.run();
        }
    }
}