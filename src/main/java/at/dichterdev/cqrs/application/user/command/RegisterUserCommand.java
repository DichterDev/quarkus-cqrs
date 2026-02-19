package at.dichterdev.cqrs.application.user.command;

import at.dichterdev.cqrs.application.common.Command;

public record RegisterUserCommand(String name, String email) implements Command {
}
