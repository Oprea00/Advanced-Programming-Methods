package Model;

import Model.adt.*;
import Model.exception.MyException;
import Model.expression.ValueExpression;
import Model.statement.IStatement;
import Model.statement.PrintStatement;
import Model.value.IntValue;
import Model.value.StringValue;
import Model.value.Value;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> executableStack;
    private IDictionary<String, Value> symbolTable;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeap heap;
    private IList<Value> output;
    private IStatement originalProgram;
    private int processID;
    private static int currentID = 0;

    public ProgramState(){
        this.executableStack = new MyStack<>();
        this.symbolTable = new MyDictionary<>();
        this.fileTable = new MyDictionary<>();
        this.output = new MyList<>();
        this.originalProgram = new PrintStatement(new ValueExpression(new IntValue(0)));
    }

    public ProgramState(IStack<IStatement> stack, IDictionary<String, Value> symbolTable, IDictionary<StringValue, BufferedReader> fileTable, IHeap heap, IList<Value> output, IStatement program){
        executableStack = stack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.heap = heap;
        this.output = output;
        processID = setCurrentID();
        originalProgram = program.deepCopy();
        stack.push(program);
    }

    synchronized private static int setCurrentID(){
        currentID = currentID + 1;
        return currentID;
    }

    public boolean isNotCompleted(){
        return !executableStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException{
        if (executableStack.isEmpty())
            throw new MyException("Program state stack is empty");
        IStatement currentStatement = executableStack.pop();
        return currentStatement.execute(this);
    }

    public IStack<IStatement> getExecutableStack() {
        return executableStack;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public IList<Value> getOutput() {
        return output;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return
                "===Thread id: " + this.processID + "===\n" +
                "===Execution Stack===\n" +
                executableStack.toString() + "\n" +
                "===Symbol Table===\n" +
                symbolTable.toString() + "\n" +
                "===File Table===\n" +
                fileTable.toString() + "\n" +
                "===Heap===\n" +
                heap.toString() + "\n" +
                "===Output List===\n"+
                output.toString() + "\n" +
                "---------------------------------------\n";
    }
}
