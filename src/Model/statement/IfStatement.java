package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.BoolType;
import Model.type.Type;
import Model.value.BoolValue;
import Model.value.Value;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

     public IfStatement(Expression e, IStatement t , IStatement el){
        expression = e;
        thenStatement = t;
        elseStatement = el;
    }

    public String toString(){
        return "(IF("+ expression.toString()+") THEN(" + thenStatement.toString()+")ELSE("+ elseStatement.toString()+"))";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value condition = expression.eval(state.getSymbolTable(), state.getHeap());
        if (condition.getType().getClass().equals(BoolType.class)){
            if (((BoolValue)condition).getValue()) {
                thenStatement.execute(state);
                return null;
            }
            else if (elseStatement != null) {
                elseStatement.execute(state);
                return null;
            }
        }
        throw new MyException("Expression is not boolean");
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type expressionType = expression.typeCheck(typeEnv);
        if (expressionType.equals(new BoolType())){
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
}
