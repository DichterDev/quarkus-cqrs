package at.dichterdev.cqrs.domain.user.event;

import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.UserId;

public record UserNameUpdatedEvent(UUID id, UserId userId) {
}
