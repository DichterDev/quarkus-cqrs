package at.dichterdev.cqrs.domain.user.model;

import org.junit.jupiter.api.Test;

import at.dichterdev.cqrs.domain.common.Money;
import at.dichterdev.cqrs.domain.user.event.UserFundsDepositedEvent;
import at.dichterdev.cqrs.domain.user.event.UserRegisteredEvent;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Currency;

public class UserTest {
    @Test
    void shouldRegisterAndEmitEvent() {
        var name = "Max Mustermann";
        var email = new Email("max.mustermann@example.com");

        User user = User.register(name, email);

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());

        var events = user.pullEvents();
        assertEquals(1, events.size());
        assertInstanceOf(UserRegisteredEvent.class, events.get(0));
    }

    @Test
    void shouldUpdateBalanceOnDeposit() {
        User user = User.register("John", new Email("john@test.com"));
        Money depositAmount = new Money(BigDecimal.valueOf(100), Currency.getInstance("EUR"));

        user.deposit(depositAmount);

        assertEquals(depositAmount, user.getBalance());

        var events = user.pullEvents();
        assertTrue(events.stream().anyMatch(e -> e instanceof UserFundsDepositedEvent));
    }
}
