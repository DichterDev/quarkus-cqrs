package at.dichterdev.cqrs.domain.user.model;

import java.util.UUID;

import at.dichterdev.cqrs.domain.common.DomainId;

public record UserId(UUID value) implements DomainId {
    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("User ID cannot be null!");
        }
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
