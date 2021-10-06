package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.exception.MyException;
import Model.expression.Expression;
import Model.type.Type;
import Model.value.Value;

public class AssignStatement implements IStatement {
    private String id;
    private Expression exp;

    public AssignStatement(String id, Expression exp){
        this.id = id;
        this.exp = exp;
    }

    public String toString() {return id + " = " + exp.toString() ;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExecutableStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        if (symbolTable.containsKey(id)){
            Value val = exp.eval(symbolTable, heap);
            Type typeId = symbolTable.getValue(id).getType();

            if (val.getType().equals(typeId))
                symbolTable.add(id,val);
            else
                throw new MyException("declared type of variable " + id + "and type of the assigned expression do not match");
        }else
            throw new MyException("the used variable" + id + "was not declared before");

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.getValue(id);
        Type typeExp = exp.typeCheck(typeEnv);
        if (typeVar.equals(typeExp)){
            return typeEnv;
        }
        else throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(id, exp.deepCopy());
    }
}
