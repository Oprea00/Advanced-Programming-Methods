package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.Type;
import Model.value.Value;

public class VariableExpression implements Expression {
    private String id;

    public VariableExpression(String ID){
        this.id = ID;
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException {
        //if (table.containsKey(id))
        //    return table.getValue(id);
        //else
         //   throw new MyException("Variable" + id + "was not declared");
        return table.getValue(id);
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.getValue(id);
    }

    @Override
    public Expression deepCopy() {
        return new VariableExpression(id);
    }
}
