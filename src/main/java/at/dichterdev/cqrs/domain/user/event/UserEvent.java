package at.dichterdev.cqrs.domain.user.event;

import at.dichterdev.cqrs.domain.common.DomainEvent;
import at.dichterdev.cqrs.domain.user.model.UserId;

public sealed interface UserEvent extends DomainEvent<UserId>
        permits
        UserRegisteredEvent,
        UserFundsDepositedEvent,
        UserFundsWithdrawnEvent,
        UserCreditLimitUpdatedEvent,
        UserEmailUpdatedEvent,
        UserNameUpdatedEvent {
}
