package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.adt.IList;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.Type;
import Model.value.Value;

public class PrintStatement implements IStatement {
    private Expression exp;

    public PrintStatement(Expression expression){
        exp = expression;
    }

    public String toString(){return "print(" + exp.toString() + ")";}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IList<Value> output = state.getOutput();
        output.add(exp.eval(state.getSymbolTable(), state.getHeap()));
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(exp.deepCopy());
    }
}
