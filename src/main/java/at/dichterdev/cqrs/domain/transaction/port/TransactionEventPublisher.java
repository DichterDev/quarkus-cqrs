package at.dichterdev.cqrs.domain.transaction.port;

import at.dichterdev.cqrs.domain.common.DomainEventPublisher;
import at.dichterdev.cqrs.domain.transaction.event.TransactionEvent;

public interface TransactionEventPublisher extends DomainEventPublisher<TransactionEvent> {

}
