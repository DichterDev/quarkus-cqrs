package at.dichterdev.cqrs.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public record Money(BigDecimal value, Currency currency) {
    public Money {
        if (value == null) {
            throw new IllegalArgumentException("Money Value cannot be null!");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Money Currency cannot be null!");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money Value cannot be negativ!");
        }

        value = value.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }

    private void checkSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money Currency mismatch!");
        }

    }

    public Money add(Money other) {
        checkSameCurrency(other);

        return new Money(this.value.add(other.value), this.currency);
    }

    public Money subtract(Money other) {
        checkSameCurrency(other);

        return new Money(this.value.subtract(other.value), this.currency);
    }

    public boolean isGreaterOrEqual(Money other) {
        checkSameCurrency(other);

        return this.value.compareTo(other.value) >= 0;
    }
}
