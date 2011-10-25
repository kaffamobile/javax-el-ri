package javax.el;

import java.util.List;
import java.util.ArrayList;

/*
 * A lambda expression in EL is a ValueExpression with parameters.  This class
 * encapsulats such information.
 */

public class LambdaExpression {

    private List<String> formalParameters = new ArrayList<String>();
    private ValueExpression expression;

    public LambdaExpression (List<String> formalParameters,
                             ValueExpression expression) {
        this.formalParameters = formalParameters;
        this.expression = expression;
    }

    /*
     * Retrieves the formal parameters of the lambda expression
     * @return The list of the parameters
     */
    public List<String> getFormalParameters() {
        return this.formalParameters;
    }

    /*
     * Invoke the lambda expression.  The supplied arguments are matched, in
     * the same order, to the formal parameters.  If there are more arguments
     * than the formal parameters, the extra arguments are ignored.  If there
     * are less arguments than the formal parameters, a ELException
     * is thrown.
     *
     * @param elContext The ELContext used for the evaluation of the expression
     * @param args The arguments for the lambda expression
     * @return The result of the lambda expression
     */
    public Object invoke(ELContext elContext, Object[] args) 
            throws ELException {
        ELManager elManager = new ELManager();
        elManager.setELContext(elContext);
        // XXX TODO need to handle clashes in VariableMapper
        int i = 0;
        for (String fParam: formalParameters) {
            if (i >= args.length) {
                // XXX
                throw new ELException();
            }
            elManager.defineBean(fParam, args[i++]);
        }
        return expression.getValue(elManager.getELContext());
    }
}
