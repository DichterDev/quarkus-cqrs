package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserRegisteredEvent(
        UUID id,
        UserId userId,
        Email email,
        Instant occurredAt)
        implements UserEvent {
    public UserRegisteredEvent(User user) {
        this(
                UUID.randomUUID(),
                user.getId(),
                user.getEmail(),
                Instant.now());
    }
}
