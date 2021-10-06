package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.IntType;
import Model.type.StringType;
import Model.type.Type;
import Model.value.IntValue;
import Model.value.StringValue;
import Model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private Expression expression;
    private String variableName;

    public ReadFileStatement(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.eval(state.getSymbolTable(), state.getHeap());
        if (value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            if(state.getFileTable().containsKey(stringValue)){
                if(state.getSymbolTable().containsKey(variableName)){
                    if(state.getSymbolTable().getValue(variableName).getType().equals(new IntType())){
                        StringValue filePath = (StringValue) value;
                        BufferedReader bufferedReader = state.getFileTable().getValue(filePath);
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null){
                                int number = 0;
                                IntValue variableValue = new IntValue(number);
                                state.getSymbolTable().add(variableName, variableValue);
                            }
                            else{
                                int number = Integer.parseInt(line);
                                IntValue variableValue = new IntValue(number);
                                state.getSymbolTable().add(variableName, variableValue);
                            }
                        }catch (IOException | NumberFormatException exception){
                            throw new MyException("Error while reading from file");
                        }
                    }
                    else throw new MyException("Variable not int type");
                }
                else throw new MyException("Variable not declared");
            }
            else throw new MyException("File not opened");
        }
        else throw new MyException("Expression value " + value.toString()+ " not String type");
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
        return "readFile(" + expression.toString() + ", " + variableName +")";
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression, variableName);
    }
}
