package Repository;

import Model.ProgramState;
import Model.adt.IList;
import Model.exception.MyException;

import java.util.List;


public interface IRepository {
    IRepository addProgramState(ProgramState programState);
    List<ProgramState> getProgramStates();
    void logProgramStateExecution(ProgramState programState) throws MyException;
    ProgramState getMainProgram();
    void setProgramStates(List<ProgramState> programStatesList);
}
