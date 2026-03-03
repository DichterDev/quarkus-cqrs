package at.dichterdev.cqrs.application.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import at.dichterdev.cqrs.application.user.command.RegisterUserCommand;
import at.dichterdev.cqrs.application.user.handler.UserCommandHandler;
import at.dichterdev.cqrs.domain.user.port.UserEventPublisher;
import at.dichterdev.cqrs.domain.user.port.UserRepository;
import io.quarkus.test.InjectMock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCommandConsistencyTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserEventPublisher publisher;

    @InjectMocks
    private UserCommandHandler handler;

    @Test
    void shouldNotPublishEvent() {
        RegisterUserCommand cmd = new RegisterUserCommand("John Doe", "john.doe@example.org");

        doThrow(new RuntimeException("Database Unique Contraint Violation")).when(repository).save(any());

        assertThrows(RuntimeException.class, () -> {
            handler.handle(cmd);
        });

        verify(publisher, never()).publish(any());
        verify(publisher, never()).publishAll(any());
    }

}
