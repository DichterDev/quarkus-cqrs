package at.dichterdev.cqrs.application.user.handler;

import at.dichterdev.cqrs.application.common.CommandHandler;
import at.dichterdev.cqrs.application.user.command.RegisterUserCommand;
import at.dichterdev.cqrs.domain.user.model.Email;
import at.dichterdev.cqrs.domain.user.model.User;
import at.dichterdev.cqrs.domain.user.port.UserEventPublisher;
import at.dichterdev.cqrs.domain.user.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserCommandHandler implements CommandHandler<RegisterUserCommand> {

    @Inject
    private UserRepository repository;
    @Inject
    private UserEventPublisher publisher;

    @Override
    @Transactional
    public void handle(RegisterUserCommand cmd) {
        User user = User.register(cmd.name(), new Email(cmd.name()));

        repository.save(user);

        publisher.publishAll(user.getEvents());
    }

}
