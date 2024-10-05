package az.scanerist.Observer;

import java.time.LocalDate;

public interface ISubscriber {
    void Update(LocalDate time);
}
