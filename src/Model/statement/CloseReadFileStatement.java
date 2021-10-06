package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.StringType;
import Model.type.Type;
import Model.value.StringValue;
import Model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    private Expression expression;

    public CloseReadFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.eval(state.getSymbolTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            if (state.getFileTable().containsKey((StringValue) value)) {
                try {
                    StringValue filePath = (StringValue) value;
                    BufferedReader bufferedReader = state.getFileTable().getValue(filePath);
                    bufferedReader.close();
                    state.getFileTable().remove(filePath);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            }
            else throw new MyException("Non-existent file");
        }
        else throw new MyException("Expression value not String type! Cannot close file!");

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type expressionType = expression.typeCheck(typeEnv);

        if(expressionType.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("Expression not StringType");
    }

    @Override
    public IStatement deepCopy() {
        return new CloseReadFileStatement(expression);
    }

    @Override
    public String toString() {
        return "closeFile(" + expression.toString() + ")";
    }
}
