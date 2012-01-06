package com.sun.el.query;

import javax.el.ELContext;
import javax.el.LambdaExpression;

import com.sun.el.lang.ELArithmetic;

class Sum extends QueryOperator {

    @Override
    public Number invoke(final ELContext context,
                         final Iterable<Object> base,
                         final Object[] params) {
        final LambdaExpression selector = getLambda("sum", params, 0, true);

        Number sum = Long.valueOf(0);
        for (Object item: base) {
            if (selector != null) {
                item = selector.invoke(context, item);
            }
            sum = ELArithmetic.add(sum, item);
        }
        return sum;
    }
}
