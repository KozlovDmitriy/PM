package cqrs;

import com.sun.istack.internal.NotNull;

/**
 * Created by ubuntu on 4/4/16.
 */
public abstract class Command {
    @NotNull
    private String commandName;

    private void generateCommandName() throws Exception {
        Class<?> enclosingClass = getClass().getEnclosingClass();
        String full;
        String postfix = Command.class.getSimpleName();
        if (enclosingClass != null) {
            full = enclosingClass.getName();
        } else {
            full = getClass().getName();
        }
        if (full.endsWith(postfix)) {
            this.commandName = full.substring(0, full.length() - postfix.length());
        } else {
            throw new Exception( "Command name \"" + full + "\" incorrect" );
        }
    }

    public Command() throws Exception {
        this.generateCommandName();
    }

    public String getCommandName() { return this.commandName; }
}
