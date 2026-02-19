package at.dichterdev.cqrs.domain.common;

import java.util.Collection;

public interface DomainEventPublisher<ID> {
    void publish(DomainEvent<ID> event);

    void publishAll(Collection<DomainEvent<ID>> events);
}
