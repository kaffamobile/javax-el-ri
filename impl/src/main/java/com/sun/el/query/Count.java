package com.sun.el.query;

import javax.el.ELContext;
import javax.el.LambdaExpression;

class Count extends QueryOperator {

    @Override
    public Number invoke(final ELContext context,
                         final Iterable<Object> base,
                         final Object[] params) {
        final LambdaExpression predicate = getLambda("count", params, 0, true);

        long count = 0;
        for (Object item: base) {
            if (predicate == null || (Boolean)predicate.invoke(context, item)) {
                count++;
            }
        }
        return Long.valueOf(count);
    }
}
