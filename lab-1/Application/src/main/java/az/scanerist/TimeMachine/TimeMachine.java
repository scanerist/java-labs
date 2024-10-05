package az.scanerist.TimeMachine;

import az.scanerist.Banks.CryptoCentralBank;

import java.time.LocalDate;

public class TimeMachine {
    private CryptoCentralBank _centralBank;
    private LocalDate _currentTime;

    public TimeMachine(CryptoCentralBank centralBank)
    {
        _centralBank = centralBank;
        _currentTime = LocalDate.now();
    }

    public void RewindTime(int years, int months, int days)
    {
        LocalDate tepmTime = _currentTime;
        _currentTime = LocalDate.of(years, months, days);
        while (tepmTime.isBefore(_currentTime))
        {
            _centralBank.NotifySubscribers(tepmTime);
            tepmTime = tepmTime.plusDays(1);
        }
    }
}
