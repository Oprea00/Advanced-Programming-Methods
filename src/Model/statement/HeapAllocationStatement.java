package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.ReferenceType;
import Model.type.Type;
import Model.value.ReferenceValue;
import Model.value.Value;

public class HeapAllocationStatement implements IStatement {
    private String variableName;
    private Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        if (symbolTable.containsKey(variableName)){
            if (symbolTable.getValue(variableName).getType() instanceof ReferenceType){
                Value value = expression.eval(symbolTable, heap);
                ReferenceType variableType = (ReferenceType) symbolTable.getValue(variableName).getType();
                if (value.getType().equals(variableType.getInnerType())){
                    Integer address = heap.insert(value);
                    symbolTable.add(variableName, new ReferenceValue(address, value.getType()));
                }
                else throw  new MyException("Values types not matching");
            }
            else throw new MyException("Variable type is not reference");
        }
        else throw new MyException("Variable not declared");

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type variableType = typeEnv.getValue(variableName);
        Type expressionType = expression.typeCheck(typeEnv);
        if (variableType.equals(new ReferenceType(expressionType))){
            return typeEnv;
        }
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "heapAllocation(" + variableName + " " + expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(variableName, expression);
    }
}
