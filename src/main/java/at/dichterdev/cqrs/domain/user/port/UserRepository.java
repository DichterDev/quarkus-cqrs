package at.dichterdev.cqrs.domain.user.port;

import java.util.Optional;
import java.util.UUID;

import at.dichterdev.cqrs.domain.user.model.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(UUID id);
}
