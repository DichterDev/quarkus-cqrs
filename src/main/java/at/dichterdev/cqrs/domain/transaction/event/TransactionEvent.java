package at.dichterdev.cqrs.domain.transaction.event;

import at.dichterdev.cqrs.domain.common.DomainEvent;
import at.dichterdev.cqrs.domain.transaction.model.TransactionId;

public interface TransactionEvent extends DomainEvent<TransactionId> {

}
