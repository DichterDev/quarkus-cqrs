package at.dichterdev.cqrs.application.transaction.command;

import at.dichterdev.cqrs.application.common.Command;
import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.transaction.model.TransactionType;
import at.dichterdev.cqrs.domain.user.model.UserId;

public record BeginTransactionCommand(
        UserId senderId,
        UserId recipientId,
        Money amount,
        String description,
        TransactionType type) implements Command {
}
