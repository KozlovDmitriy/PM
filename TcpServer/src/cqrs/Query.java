package cqrs;

import com.sun.istack.internal.NotNull;

/**
 * Created by ubuntu on 4/4/16.
 */
public abstract class Query<TResult> extends CqrsOperation {
    @Override
    protected String getPostfix() { return Query.class.getSimpleName(); }
}
