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

public class WriteHeapStatement implements IStatement {
    private String variableName;
    Expression expression;

    public WriteHeapStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        if (symbolTable.containsKey(variableName)){
            Value value = symbolTable.getValue(variableName);
            if (value.getType() instanceof ReferenceType){
                ReferenceValue referenceValue = (ReferenceValue) value;
                Type innerType = ((ReferenceType)referenceValue.getType()).getInnerType();
                if (heap.containsKey(referenceValue.getAddress())){
                    Value value1 = expression.eval(symbolTable, heap);
                    if (value1.getType().equals(innerType)) {
                        heap.update(referenceValue.getAddress(), value1);
                        return null;
                    }
                    else throw new MyException("Invalid inner type");
                }
                else throw new MyException("Invalid address");
            }
            else throw new MyException("Invalid variable type");
        }
        else throw new MyException("Variable not declared");
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type variableType = typeEnv.getValue(variableName);
        Type expressionType = expression.typeCheck(typeEnv);
        if (variableType.equals(new ReferenceType(expressionType))){
            return typeEnv;
        }
        else
            throw new MyException("Write Heap stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString(){
        return "writeHeap(" + variableName + ", " + expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(variableName, expression.deepCopy());
    }
}
