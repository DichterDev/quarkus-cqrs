package at.dichterdev.cqrs.domain.user.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.event.UserCreditLimitUpdatedEvent;
import at.dichterdev.cqrs.domain.user.event.UserEmailUpdatedEvent;
import at.dichterdev.cqrs.domain.user.event.UserEvent;
import at.dichterdev.cqrs.domain.user.event.UserFundsDepositedEvent;
import at.dichterdev.cqrs.domain.user.event.UserFundsWithdrawnEvent;
import at.dichterdev.cqrs.domain.user.event.UserNameUpdatedEvent;
import at.dichterdev.cqrs.domain.user.event.UserRegisteredEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {
    private final UserId id;
    private String name;
    private Email email;

    @Builder.Default
    private Money balance = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));

    @Builder.Default
    private Money creditLimit = new Money(BigDecimal.valueOf(1000), Currency.getInstance("EUR"));

    @Builder.Default
    private List<UserEvent> events = new ArrayList<>();

    public static User register(String name, Email email) {
        User user = User.builder().id(UserId.generate()).name(name).email(email).build();

        user.getEvents().add(new UserRegisteredEvent(user.getId(), user.getEmail()));

        return user;
    }

    public void deposit(Money amount) {
        this.balance = this.balance.add(amount);

        this.events.add(new UserFundsDepositedEvent(this.id, amount));
    }

    public void withdraw(Money amount) {
        this.balance = this.balance.subtract(amount);

        this.events.add(new UserFundsWithdrawnEvent(this.id, amount));
    }

    public void updateCreditLimit(Money amount) {
        Money old = this.creditLimit;
        this.creditLimit = amount;

        this.events.add(new UserCreditLimitUpdatedEvent(this.id, old, this.creditLimit));
    }

    public void updateCredentials(String name, Email email) {
        if (name != null) {
            String old = this.name;
            this.name = name;

            this.events.add(new UserNameUpdatedEvent(this.id, old, this.name));
        }

        if (email != null) {
            Email old = this.email;
            this.email = email;

            this.events.add(new UserEmailUpdatedEvent(this.id, old, this.email));
        }
    }
}
