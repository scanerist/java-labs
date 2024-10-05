package az.scanerist.Repositories;

import az.scanerist.Banks.CryptoCentralBank;
import az.scanerist.Banks.IBank;
import az.scanerist.Models.CryptoDepositRate;
import az.scanerist.Models.CryptoDepositRateWithCryptoBankIndex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for banks
 */

public class CryptoBankRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/banks_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    /**
     * Constructor
     */
    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private CryptoCentralBank centralBank = CryptoCentralBank.getInstance();

    /**
     * Constructor
     *
     * @return {@code List<IBank>} - list of all banks
     */
    public List<IBank> getAllBanks() {
        List<IBank> banks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ArrayList<CryptoDepositRateWithCryptoBankIndex> depositRates = new ArrayList<>();
            String depositInfo = "SELECT * FROM deposit_rate";
            ResultSet resultSetDeposit = statement.executeQuery(depositInfo);
            while (resultSetDeposit.next()) {
                int bankId = resultSetDeposit.getInt("bank_id");
                Float lowerLimit = resultSetDeposit.getFloat("lower_limit");
                Float upperLimit = resultSetDeposit.getFloat("upper_limit");
                Float rate = resultSetDeposit.getFloat("rate");
                CryptoDepositRate depositRate = new CryptoDepositRate(lowerLimit, upperLimit, rate);
                depositRates.add(new CryptoDepositRateWithCryptoBankIndex(depositRate, bankId));
            }
            String bankInfo = "SELECT * FROM banks";
            ResultSet resultSet = statement.executeQuery(bankInfo);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float debitInterest = resultSet.getFloat("debit_interest");
                Float creditCommission = resultSet.getFloat("credit_commission");
                Float maxWithdraw = resultSet.getFloat("max_withdraw");
                Float maxRemittance = resultSet.getFloat("max_remittance");
                IBank bank = centralBank.CreateBank(
                        name,
                        debitInterest,
                        depositRates.stream().filter(x -> x.getBankIndex() == id).map(CryptoDepositRateWithCryptoBankIndex::getDepositRate).toList(),
                        creditCommission,
                        maxWithdraw,
                        maxRemittance
                );
                banks.add(bank);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return banks;
    }

}
