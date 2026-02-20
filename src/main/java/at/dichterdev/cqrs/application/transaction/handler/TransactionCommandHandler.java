package at.dichterdev.cqrs.application.transaction.handler;

import at.dichterdev.cqrs.application.common.CommandHandler;
import at.dichterdev.cqrs.application.transaction.command.BeginTransactionCommand;
import at.dichterdev.cqrs.domain.transaction.model.Transaction;
import at.dichterdev.cqrs.domain.transaction.port.TransactionEventPublisher;
import at.dichterdev.cqrs.domain.transaction.port.TransactionRepository;
import jakarta.inject.Inject;

public class TransactionCommandHandler implements CommandHandler<BeginTransactionCommand> {

    @Inject
    private TransactionRepository repository;

    @Inject
    private TransactionEventPublisher publisher;

    @Override
    public void handle(BeginTransactionCommand cmd) {
        var transaction = Transaction.begin(
                cmd.senderId(),
                cmd.recipientId(),
                cmd.amount(),
                cmd.description(),
                cmd.type());

        repository.save(transaction);

        publisher.publishAll(transaction.pullEvents());
    }

}
