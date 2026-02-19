package at.dichterdev.cqrs.domain.user.model;

public record Address(String street, String country, String zip) {
    public Address {
        if (street == null) {
            throw new IllegalArgumentException("Address Street cannot be null!");
        }

        if (country == null) {
            throw new IllegalArgumentException("Address Country cannot be null!");
        }

        if (zip == null) {
            throw new IllegalArgumentException("Address ZIP cannot be null!");
        }
    }
}
