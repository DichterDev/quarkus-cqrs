package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserEmailUpdatedEvent(UUID id, UserId entityId, Email from, Email to, Instant occurredAt)
        implements UserEvent {
    public UserEmailUpdatedEvent(UserId userId, Email from, Email to) {
        this(UUID.randomUUID(), userId, from, to, Instant.now());
    }
}
