package com.sun.el.query;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import javax.el.ELContext;

class Distinct extends QueryOperator {
    @Override
    public Iterable<Object> invoke(final ELContext context,
                                   final Iterable<Object> base,
                                   final Object[] params) {
        return new Iterable<Object>() {
            @Override
            public Iterator<Object> iterator() {
                return new BaseIterator(base) {
                    private Set<Object> set = new HashSet<Object>();

                    @Override
                    void doItem(Object item) {
                        if (set.add(item)) {
                            yield(item);
                        }
                    }
                };
            }
        };
    }
}

