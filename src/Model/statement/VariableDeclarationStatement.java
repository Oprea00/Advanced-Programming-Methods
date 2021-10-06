package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.exception.MyException;
import Model.type.*;
import Model.value.*;

public class VariableDeclarationStatement implements IStatement {
    private String name;
    private Type type;
    private Value value;

    public VariableDeclarationStatement(String name, Type type, Value value){
        this.name = name;
        this.type = type;
        this.value = value;
    }
    public VariableDeclarationStatement(String name, Type type){
        this.name = name;
        this.type = type;
        this.value = null;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (symbolTable.containsKey(name)){
            throw new MyException("Variable " + name + " was declared before");
        }
        else{
            symbolTable.add(name, type.getDefaultValue());
        }

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name, type);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type.deepCopy(), value);
    }

    private void throwTypesNotMatching() throws MyException {
        throw new MyException("Value with type "+ value.getType().toString() + " was given for variable with type " +type.toString()+" and name" + name);
    }
}
