package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserNameUpdatedEvent(
        UUID id,
        UserId userId,
        String from,
        String to,
        Instant occurredAt)
        implements UserEvent {
    public UserNameUpdatedEvent(User user, String from) {
        this(
                UUID.randomUUID(),
                user.getId(),
                from,
                user.getName(),
                Instant.now());
    }
}
