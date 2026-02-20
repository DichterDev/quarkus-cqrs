package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserFundsWithdrawnEvent(
        UUID id,
        UserId userId,
        Money amount,
        Instant occurredAt)
        implements UserEvent {
    public UserFundsWithdrawnEvent(User user, Money amount) {
        this(
                UUID.randomUUID(),
                user.getId(),
                amount,
                Instant.now());
    }
}
