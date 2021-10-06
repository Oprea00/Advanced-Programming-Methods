package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.Type;
import Model.value.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value v){
        value = v;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException {
        return value;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public Expression deepCopy() {
        return new ValueExpression(value);
    }
}
