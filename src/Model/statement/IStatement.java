package Model.statement;

import Model.ProgramState;
import Model.adt.IDictionary;
import Model.exception.MyException;
import Model.type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws MyException;
    IStatement deepCopy();
}
