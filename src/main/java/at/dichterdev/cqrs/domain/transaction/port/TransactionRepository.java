package at.dichterdev.cqrs.domain.transaction.port;

import java.util.Optional;

import at.dichterdev.cqrs.domain.transaction.model.Transaction;
import at.dichterdev.cqrs.domain.transaction.model.TransactionId;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(TransactionId id);
}
