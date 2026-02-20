package at.dichterdev.cqrs.domain.transaction.event;

import java.time.Instant;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.transaction.model.Transaction;
import at.dichterdev.cqrs.domain.transaction.model.TransactionId;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record TransactionCompletedEvent(
        UUID id,
        TransactionId transactionId,
        UserId senderId,
        UserId recipientId,
        Money amount,
        String description,
        Instant occurredAt) implements TransactionEvent {

    public TransactionCompletedEvent(Transaction transaction) {
        this(
                UUID.randomUUID(),
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getRecipientId(),
                transaction.getAmount(),
                transaction.getDescription(),
                Instant.now());
    }
}
