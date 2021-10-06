package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.exception.MyException;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.Type;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.Value;

public class RelationalExpression implements Expression {
    private Expression expression1;
    private Expression expression2;
    private int operator; // 1-<  2-<=  3-==  4-!=  5->  6->=

    public RelationalExpression(int op, Expression expr1, Expression expr2){
        operator = op;
        expression1 = expr1;
        expression2 = expr2;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeap heap) throws MyException {
        Value value1, value2;
        value1= expression1.eval(table, heap);
        if(value1.getType().equals(new IntType())) {
            value2 = expression2.eval(table, heap);
            if(value1.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) value1;
                IntValue i2 = (IntValue) value2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operator == 1) return new BoolValue(n1<n2);
                if (operator == 2) return new BoolValue(n1<=n2);
                if (operator == 3) return new BoolValue(n1==n2);
                if (operator == 4) return new BoolValue(n1!=n2);
                if (operator == 5) return new BoolValue(n1>n2);
                if (operator == 6) return new BoolValue(n1>=n2);
            }
            else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if ( type1.equals(new IntType())){
            if (type2.equals(new IntType())){
                return new BoolType();
            }
            else
                throw new MyException("Relational Expression, second operand is not an integer");
        }
        else
            throw new MyException("Relational Expression, first operand is not an integer");
    }

    @Override
    public String toString() {
        if (operator == 1)
            return "(" + expression1.toString() + "<" + expression2.toString() + ")";
        else if (operator == 2)
            return "(" + expression1.toString() + "<=" + expression2.toString() + ")";
        else if (operator == 3)
            return "(" + expression1.toString() + "==" + expression2.toString() + ")";
        else if (operator == 4)
            return "(" + expression1.toString() + "!=" + expression2.toString() + ")";
        else if (operator == 5)
            return "(" + expression1.toString() + ">" + expression2.toString() + ")";
        else if (operator == 6)
            return "(" + expression1.toString() + ">=" + expression2.toString() + ")";
        else
            return null;
    }

    @Override
    public Expression deepCopy() {
        return new RelationalExpression(operator, expression1.deepCopy(), expression2.deepCopy());
    }
}
