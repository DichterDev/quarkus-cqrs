package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserEmailUpdatedEvent(
        UUID id,
        UserId userId,
        Email from,
        Email to,
        Instant occurredAt)
        implements UserEvent {
    public UserEmailUpdatedEvent(User user, Email from) {
        this(
                UUID.randomUUID(),
                user.getId(),
                from,
                user.getEmail(),
                Instant.now());
    }
}
