package cqrs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ubuntu on 4/4/16.
 */
public final class CqrsDispatcher {
    public static <T> T as(Class<T> clazz, Object o) {
        if(clazz.isInstance(o)){
            return clazz.cast(o);
        }
        return null;
    }

    private CommandHandler createCommandHandlerByCommand(Command command)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String commandName = command.getOperationName();
        String commandHandlerName = commandName + CommandHandler.class.getSimpleName();
        Class<?> clazz = Class.forName(commandHandlerName);
        Constructor<?> ctor = clazz.getConstructor(String.class);
        Object object = ctor.newInstance(new Object[] { command });
        return as(CommandHandler.class, object);
    }

    private <T> QueryHandler<Query<T>, T> createQueryHandlerByQuery(Query<T> query)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String queryName = query.getOperationName();
        String queryHandlerName = queryName + QueryHandler.class.getSimpleName();
        Class<?> clazz = Class.forName(queryHandlerName);
        Constructor<?> ctor = clazz.getConstructor(String.class);
        Object object = ctor.newInstance(new Object[] { query });
        return (QueryHandler<Query<T>, T>) object;
    }

    public void Run(Command command) {
        try {
            CommandHandler handler = this.createCommandHandlerByCommand(command);
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

    public <T> T Run(Query<T> query) {
        T result = null;
        try {
            QueryHandler<Query<T>, T> handler = this.createQueryHandlerByQuery(query);
            handler.Preprocessing();
            if (handler.IsValid()) {
                result = handler.Handle();
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
        return result;
    }
}
