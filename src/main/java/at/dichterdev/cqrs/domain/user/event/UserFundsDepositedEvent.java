package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.DomainEvent;
import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserFundsDepositedEvent(UUID id, UserId entityId, Money amount, Instant occurredAt)
        implements UserEvent {
    public UserFundsDepositedEvent(UserId userId, Money amount) {
        this(UUID.randomUUID(), userId, amount, Instant.now());
    }
}
