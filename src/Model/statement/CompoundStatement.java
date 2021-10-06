package Model.statement;


import Model.ProgramState;
import Model.adt.IDictionary;
import Model.adt.IStack;
import Model.exception.MyException;
import Model.type.Type;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement firstStmt, IStatement secondStmt){
        first = firstStmt;
        second = secondStmt;
    }

    public String toString(){
        return "(" + first.toString() + ";" + second.toString()+ ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExecutableStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }
}
