package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserCreditLimitUpdatedEvent(
        UUID id,
        UserId userId,
        Money from,
        Money to,
        Instant occurredAt)
        implements UserEvent {
    public UserCreditLimitUpdatedEvent(User user, Money from) {
        this(
                UUID.randomUUID(),
                user.getId(),
                from,
                user.getCreditLimit(),
                Instant.now());
    }
}
