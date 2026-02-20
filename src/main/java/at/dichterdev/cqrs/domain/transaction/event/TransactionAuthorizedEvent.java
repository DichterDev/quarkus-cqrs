package at.dichterdev.cqrs.domain.transaction.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.transaction.model.TransactionId;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record TransactionAuthorizedEvent(
        UUID id,
        TransactionId entityId,
        UserId senderId,
        UserId recipientId,
        Money amount,
        String description,
        Instant occurredAt) implements TransactionEvent {

    public TransactionAuthorizedEvent(
            TransactionId transactionId,
            UserId senderId,
            UserId recipientId,
            Money amount,
            String description) {
        this(UUID.randomUUID(), transactionId, senderId, recipientId, amount, description, Instant.now());
    }
}
