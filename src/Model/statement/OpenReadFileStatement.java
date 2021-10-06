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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    private Expression expression;

    public OpenReadFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.eval(state.getSymbolTable(), state.getHeap());
        if (value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            if (state.getFileTable().containsKey(stringValue)){
                throw new MyException("The key" + stringValue.toString() + "already exists in the FileTable");
            }
            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(stringValue.getValue()));
                state.getFileTable().add(stringValue, bufferedReader);
            }
            catch (FileNotFoundException exception) {
                throw new MyException("Error while trying to open file" + stringValue.toString() + "for reading");
            }
        }
        else throw new MyException("Expression value not String type");
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
    public String toString() {
        return "openFile(" + expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new OpenReadFileStatement(expression);
    }
}
