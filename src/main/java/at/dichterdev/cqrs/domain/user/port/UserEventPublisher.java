package at.dichterdev.cqrs.domain.user.port;

import at.dichterdev.cqrs.domain.common.DomainEventPublisher;
import at.dichterdev.cqrs.domain.user.model.UserId;

public interface UserEventPublisher extends DomainEventPublisher<UserId> {

}
