package cqrs;

/**
 * Created by ubuntu on 4/4/16.
 */
public abstract class QueryHandler<T extends Query<TResult>, TResult> {
    private T query;

    public QueryHandler(T query) {
        this.query = query;
    }

    public T getQuery() { return this.query; }

    abstract void Preprocessing();
    abstract boolean IsValid();
    abstract TResult Handle();
    abstract void Postprocessing();
}
