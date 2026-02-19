package at.dichterdev.cqrs.domain.common;

import java.util.UUID;

public interface DomainId {
    UUID value();
}
