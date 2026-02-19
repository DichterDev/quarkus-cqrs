package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserCreditLimitUpdatedEvent(UUID id, UserId entityId, Money from, Money to, Instant occurredAt)
        implements UserEvent {
    public UserCreditLimitUpdatedEvent(UserId userId, Money from, Money to) {
        this(UUID.randomUUID(), userId, from, to, Instant.now());
    }
}
