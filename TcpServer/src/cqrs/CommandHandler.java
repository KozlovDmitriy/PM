package cqrs;

/**
 * Created by ubuntu on 4/4/16.
 */
public abstract class CommandHandler<T extends Command> {
    private T command;

    public CommandHandler(T command) {
        this.command = command;
    }

    public T getCommand() { return this.command; }

    abstract void Preprocessing();
    abstract boolean IsValid();
    abstract void Handle();
    abstract void Postprocessing();
}
