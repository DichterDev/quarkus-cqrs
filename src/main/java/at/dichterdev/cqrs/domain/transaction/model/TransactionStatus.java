package at.dichterdev.cqrs.domain.transaction.model;

public enum TransactionStatus {
    PENDING,
    AUTHORIZED,
    COMPLETED,
    CANCELLED,
    FAILED
}
