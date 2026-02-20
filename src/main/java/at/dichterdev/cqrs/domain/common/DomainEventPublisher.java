package at.dichterdev.cqrs.domain.common;

import java.util.Collection;

public interface DomainEventPublisher {

    void publish(DomainEvent event);

    void publishAll(Collection<DomainEvent> events);
}
