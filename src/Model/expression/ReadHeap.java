package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.ReferenceType;
import Model.type.Type;
import Model.value.ReferenceValue;
import Model.value.Value;

public class ReadHeap implements Expression {
    private Expression expression;

    public ReadHeap(Expression expression){
        this.expression = expression;
    }


    @Override
    public Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException {
        Value value = expression.eval(table, heap);
        if(value instanceof ReferenceValue){
            ReferenceValue referenceValue = (ReferenceValue) value;
            int address = referenceValue.getAddress();
            if (heap.containsKey(address)){
                return heap.getValue(address);
            }
            else throw new MyException("Invalid address");
        }
        else throw new MyException("Not a Reference Value");
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType){
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInnerType();
        }
        else  throw new MyException("the Read Heap argument is not a Ref Type");
    }

    @Override
    public String toString(){
        return "readHeap("+ expression.toString()+")";
    }

    @Override
    public Expression deepCopy() {
        return new ReadHeap(expression.deepCopy());
    }
}
