package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserNameUpdatedEvent(UUID id, UserId entityId, String from, String to, Instant occurredAt)
        implements UserEvent {
    public UserNameUpdatedEvent(UserId userId, String from, String to) {
        this(UUID.randomUUID(), userId, from, to, Instant.now());
    }
}
