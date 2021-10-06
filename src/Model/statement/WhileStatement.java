package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.adt.IStack;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.BoolType;
import Model.type.Type;
import Model.value.BoolValue;
import Model.value.Value;

public class WhileStatement implements IStatement {
    private Expression expression;
    private IStatement statement;

    public WhileStatement(Expression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExecutableStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (expression.eval(symbolTable, state.getHeap()).getType().equals(new BoolType())){
            BoolValue condition = (BoolValue) expression.eval(symbolTable, state.getHeap());
            if (condition.getValue()){
                stack.push(new WhileStatement(expression, statement));
                stack.push(statement);
            }
        }
        else throw new MyException("Expression is not bool");

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type expressionType = expression.typeCheck(typeEnv);
        if (expressionType.equals(new BoolType())){
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public String toString() {
        return "( while(" + expression.toString() + ")" + statement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression, statement);
    }
}
