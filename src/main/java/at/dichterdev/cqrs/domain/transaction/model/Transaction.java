package at.dichterdev.cqrs.domain.transaction.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.transaction.event.TransactionAuthorizedEvent;
import at.dichterdev.cqrs.domain.transaction.event.TransactionBeganEvent;
import at.dichterdev.cqrs.domain.transaction.event.TransactionCancelledEvent;
import at.dichterdev.cqrs.domain.transaction.event.TransactionCompletedEvent;
import at.dichterdev.cqrs.domain.transaction.event.TransactionEvent;
import at.dichterdev.cqrs.domain.transaction.event.TransactionFailedEvent;
import at.dichterdev.cqrs.domain.user.model.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static at.dichterdev.cqrs.domain.transaction.model.TransactionStatus.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Transaction {
    private final TransactionId id;

    private final UserId senderId;
    private final UserId recipientId;
    private final Money amount;

    @Builder.Default
    private final String description = String.valueOf("");

    @Builder.Default
    private final TransactionType type = TransactionType.TRANSFER;

    @Builder.Default
    private TransactionStatus status = PENDING;

    @Builder.Default
    private List<TransactionEvent> events = new ArrayList<>();

    public static Transaction begin(
            UserId senderId,
            UserId recipientId,
            Money amount,
            String description,
            TransactionType type) {
        if (senderId == null) {
            throw new IllegalArgumentException("Transaction Sender ID cannot be null!");
        }

        if (recipientId == null) {
            throw new IllegalArgumentException("Transaction Recipient ID cannot be null!");
        }

        if (amount == null) {
            throw new IllegalArgumentException("Transaction Amount cannot be null!");
        }

        if (!amount.isGreaterOrEqual(new Money(BigDecimal.ZERO, Currency.getInstance("EUR")))) {
            throw new IllegalArgumentException("Transaction Amount cannot be negative!");
        }

        var builder = Transaction.builder()
                .id(TransactionId.generate())
                .senderId(senderId)
                .recipientId(recipientId)
                .amount(amount);

        if (description != null) {
            builder.description(description);
        }

        if (type != null) {
            builder.type(type);
        }

        Transaction transaction = builder.build();

        transaction.getEvents().add(new TransactionBeganEvent(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getRecipientId(),
                transaction.getAmount(),
                transaction.getDescription()));

        return transaction;
    }

    public void authorize() {
        if (this.status != PENDING) {
            throw new IllegalArgumentException("Transaction cannot be authorized, Status must be PENDING!");
        }

        this.status = AUTHORIZED;

        this.events.add(new TransactionAuthorizedEvent(
                this.id,
                this.senderId,
                this.recipientId,
                this.amount,
                this.description));
    }

    public void complete() {
        if (this.status != AUTHORIZED) {
            throw new IllegalArgumentException("Transaction cannot be completed, Status must be AUTHORIZED!");
        }

        this.status = COMPLETED;

        this.events.add(new TransactionCompletedEvent(
                this.id,
                this.senderId,
                this.recipientId,
                this.amount,
                this.description));
    }

    public void cancel() {
        if (this.status == COMPLETED) {
            throw new IllegalArgumentException("Transaction cannot be cancelled, Status already COMPLETED");
        }

        this.status = CANCELLED;

        this.events.add(new TransactionCancelledEvent(
                this.id,
                this.senderId,
                this.recipientId,
                this.amount,
                this.description));
    }

    public void fail() {
        if (this.status == COMPLETED) {
            throw new IllegalArgumentException("Transaction cannot be failed, Status already COMPLETED");
        }

        this.status = FAILED;

        this.events.add(new TransactionFailedEvent(
                this.id,
                this.senderId,
                this.recipientId,
                this.amount,
                this.description));
    }
}
