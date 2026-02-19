package at.dichterdev.cqrs.domain.user.model;

public record Email(String value) {
    public Email {
        if (value == null) {
            throw new IllegalArgumentException("Email cannot be null!");
        }

        if (!value.contains("@")) {
            throw new IllegalArgumentException("Email must contain @ character!");
        }
    }
}
