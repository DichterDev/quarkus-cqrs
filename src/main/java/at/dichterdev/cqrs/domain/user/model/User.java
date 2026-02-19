package at.dichterdev.cqrs.domain.user.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import at.dichterdev.cqrs.domain.common.Money;
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
    private List<Object> events = new ArrayList<>();

    public static User register(String name, Email email) {
        User user = User.builder().id(UserId.generate()).name(name).email(email).build();

        return user;
    }

    public void deposit(Money amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void updateCreditLimit(Money amount) {
        this.creditLimit = amount;
    }

    public void updateCredentials(String name, Email email) {
        if (name != null) {
            this.name = name;
        }

        if (email != null) {
            this.email = email;
        }
    }
}
