package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.DomainEvent;
import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserEmailUpdatedEvent(UUID id, UserId userId, Email from, Email to, Instant occurredAt)
        implements DomainEvent<UserId> {
    public UserEmailUpdatedEvent(UserId userId, Email from, Email to) {
        this(UUID.randomUUID(), userId, from, to, Instant.now());
    }
}
