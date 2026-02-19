package at.dichterdev.cqrs.application.common;

public interface CommandHandler<C extends Command> {
    void handle(C cmd);
}
