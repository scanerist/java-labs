package az.scanerist.Observer;

import java.time.LocalDate;

public interface IPublisher {
    void AddSubscriber(ISubscriber subscriber);
    void RemoveSubscriber(ISubscriber subscriber);
    void NotifySubscribers(LocalDate time);
}
