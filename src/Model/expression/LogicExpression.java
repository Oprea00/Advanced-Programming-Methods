package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.BoolType;
import Model.type.Type;
import Model.value.BoolValue;
import Model.value.Value;


public class LogicExpression implements Expression {
    Expression expression1;
    Expression expression2;
    int operator; //1-and, 2-or

    public LogicExpression(int op, Expression exp1, Expression exp2){
        operator = op;
        expression1 = exp1;
        expression2 = exp2;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException {
        Value v1,v2;
        v1= expression1.eval(table, heap);
        if(v1.getType().equals(new BoolType())){
            v2= expression2.eval(table, heap);
            if(v1.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                Boolean n1,n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operator == 1) return new BoolValue(n1&&n2);
                if (operator == 2) return new BoolValue(n1||n2);
            }else
                throw new MyException("second operand is not boolean");
        }else
            throw new MyException("first operand is not boolean");

        return null;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }
            else
                throw new MyException("second operand is not boolean");
        }
        else
            throw new MyException("first operand is not boolean");
    }

    @Override
    public String toString() {
        if (operator == 1)
            return expression1.toString() + "and" + expression2.toString();
        else if (operator == 2)
            return expression1.toString() + "or" + expression2.toString();
        else return null;
    }

    @Override
    public Expression deepCopy() {
        return new LogicExpression(operator, expression1.deepCopy(), expression2.deepCopy());
    }
}
