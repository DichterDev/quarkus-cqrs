package at.dichterdev.cqrs.domain.common;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID id();

    Instant occurredAt();
}
