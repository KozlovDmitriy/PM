package cqrs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ubuntu on 4/4/16.
 */
public final class CommandDispatcher {
    public static <T> T as(Class<T> clazz, Object o) {
        if(clazz.isInstance(o)){
            return clazz.cast(o);
        }
        return null;
    }

    private CommandHandler createCommandhandlerByCommand(Command command)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String commandName = command.getCommandName();
        String commandHandlerName = commandName + CommandHandler.class.getSimpleName();
        Class<?> clazz = Class.forName(commandHandlerName);
        Constructor<?> ctor = clazz.getConstructor(String.class);
        Object object = ctor.newInstance(new Object[] { command });
        return as(CommandHandler.class, object);
    }

    public void Run(Command command) {
        try {
            CommandHandler handler = this.createCommandhandlerByCommand(command);
            handler.Preprocessing();
            if (handler.IsValid()) {
                handler.Handle();
                handler.Postprocessing();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
