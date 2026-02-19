package at.dichterdev.cqrs.domain.user.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserRegisteredEvent(UUID id, UserId userId, Email email, Instant occurredAt) {
    public UserRegisteredEvent(UserId userId, Email email) {
        this(UUID.randomUUID(), userId, email, Instant.now());
    }
}
