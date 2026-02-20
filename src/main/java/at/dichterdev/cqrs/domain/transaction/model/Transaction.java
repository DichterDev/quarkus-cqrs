package at.dichterdev.cqrs.domain.transaction.model;

import java.util.UUID;

import at.dichterdev.cqrs.domain.common.Money;
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

    public static Transaction begin(UserId senderId, UserId recipientId, Money amount) {
        return Transaction.builder().id(TransactionId.generate()).senderId(senderId).recipientId(recipientId)
                .amount(amount).build();
    }

    public static Transaction begin(UserId senderId, UserId recipientId, Money amount, String description) {
        return Transaction.builder().id(TransactionId.generate()).senderId(senderId).recipientId(recipientId)
                .amount(amount).description(description).build();
    }

    public static Transaction begin(UserId senderId, UserId recipientId, Money amount, TransactionType type) {
        return Transaction.builder().id(TransactionId.generate()).senderId(senderId).recipientId(recipientId)
                .amount(amount).type(type).build();
    }

    public static Transaction begin(UserId senderId, UserId recipientId, Money amount, String description,
            TransactionType type) {
        return Transaction.builder().id(TransactionId.generate()).senderId(senderId).recipientId(recipientId)
                .amount(amount).description(description).type(type).build();
    }

    public void authorize() {
        if (this.status != PENDING) {
            throw new IllegalArgumentException("Transaction cannot be authorized, Status must be PENDING!");
        }

        this.status = AUTHORIZED;
    }

    public void complete() {
        if (this.status != AUTHORIZED) {
            throw new IllegalArgumentException("Transaction cannot be completed, Status must be AUTHORIZED!");
        }

        this.status = COMPLETED;
    }

    public void cancel() {
        if (this.status == COMPLETED) {
            throw new IllegalArgumentException("Transaction cannot be cancelled, Status already COMPLETED");
        }

        this.status = CANCELLED;
    }

    public void fail() {
        if (this.status == COMPLETED) {
            throw new IllegalArgumentException("Transaction cannot be failed, Status already COMPLETED");
        }

        this.status = FAILED;
    }
}
