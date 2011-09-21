/* Generated By:JJTree: Do not edit this line. AstAssign.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=Ast,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.sun.el.parser;

import javax.el.ELException;
import javax.el.VariableMapper;
import com.sun.el.ValueExpressionImpl;
import com.sun.el.lang.EvaluationContext;

public
class AstAssign extends SimpleNode {
    public AstAssign(int id) {
        super(id);
    }

    /*
     * Return true if the identifier can be resolved by a ELResolver.
     */
    private boolean canResolve(EvaluationContext ctx, String name) {
        ctx.setPropertyResolved(false);
        ctx.getELResolver().getValue(ctx, null, name);
        return ctx.isPropertyResolved();
    }

    public Object getValue(EvaluationContext ctx)
            throws ELException {
        if (children[0] instanceof AstIdentifier) {
            // if the target is an EL variable or if it cannot be resolvered
            // by a ELResolver, set the variable to the fhs expression
            String name = ((AstIdentifier)children[0]).image;
            VariableMapper varMapper = ctx.getTargetVariableMapper();
            if (varMapper != null && varMapper.resolveVariable(name) != null ||
                        !canResolve(ctx, name)) {
                // set the variable to the new expression
                varMapper.setVariable(name, new ValueExpressionImpl(
                    "assignment operator",
                    children[1],
                    ctx.getFunctionMapper(),
                    ctx.getVariableMapper(),
                    varMapper,
                    null));
                return children[1].getValue(ctx);
            }
        }
                
        Object value = children[1].getValue(ctx);
        children[0].setValue(ctx, value);
        return value;
    }
}
/* JavaCC - OriginalChecksum=22c1f3fa0c12632335bc720b5e002c0f (do not edit this line) */