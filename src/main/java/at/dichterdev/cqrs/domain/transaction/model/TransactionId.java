package at.dichterdev.cqrs.domain.transaction.model;

import java.util.UUID;

import at.dichterdev.cqrs.domain.common.DomainId;

public record TransactionId(UUID value) implements DomainId {
    public TransactionId {
        if (value == null) {
            throw new IllegalArgumentException("Transaction ID cannot be null!");
        }
    }

    public static TransactionId generate() {
        return new TransactionId(UUID.randomUUID());
    }
}
