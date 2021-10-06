package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.Type;
import Model.value.Value;

public interface Expression {
    Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException;
    Type typeCheck(IDictionary<String,Type> typeEnv) throws MyException;
    Expression deepCopy();
}
