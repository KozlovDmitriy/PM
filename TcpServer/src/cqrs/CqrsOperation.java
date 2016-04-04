package cqrs;

import com.sun.istack.internal.NotNull;

/**
 * Created by ubuntu on 4/4/16.
 */
public abstract class CqrsOperation {
    @NotNull
    private String name;
    private String postfix = getPostfix();

    private void generateOperationName() throws Exception {
        Class<?> enclosingClass = getClass().getEnclosingClass();
        String full;
        if (enclosingClass != null) {
            full = enclosingClass.getName();
        } else {
            full = getClass().getName();
        }
        if (full.endsWith(postfix)) {
            this.name = full.substring(0, full.length() - postfix.length());
        } else {
            throw new Exception( postfix + " name \"" + full + "\" incorrect" );
        }
    }

    protected abstract String getPostfix();

    public String getOperationName() { return this.name; }
}
