package at.dichterdev.cqrs.domain.common;

import java.util.Collection;

public interface DomainEventPublisher<E extends DomainEvent<?>> {

    void publish(E event);

    void publishAll(Collection<E> events);
}
