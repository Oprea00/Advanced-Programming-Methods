package Model.statement;

import Model.ProgramState;
import Model.adt.*;
import Model.exception.MyException;
import Model.type.Type;
import Model.value.StringValue;
import Model.value.Value;

import java.io.BufferedReader;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> executableStack = new MyStack<IStatement>();
        IDictionary<String, Value> symbolTable = state.getSymbolTable().deepCopy();
        IList<Value> output = state.getOutput();
        IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap heap = state.getHeap();

        return new ProgramState(executableStack, symbolTable, fileTable, heap, output, statement);
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        return statement.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }
}
