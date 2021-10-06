package Repository;

import Model.ProgramState;
import Model.adt.IList;
import Model.adt.MyList;
import Model.exception.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


public class Repository implements IRepository {
    private List<ProgramState> programStateList;
    private String logFilePath;

    public Repository(String logFilePath){
        this.logFilePath = logFilePath;
        //programStateList = new MyList<>();
        programStateList = new LinkedList<>();
    }

    @Override
    public IRepository addProgramState(ProgramState programState) {
        programStateList.add(programState);
        return this;
    }

    @Override
    public ProgramState getMainProgram() {
        return programStateList.get(0);
    }

    @Override
    public void setProgramStates(List<ProgramState> programStatesList) {
        this.programStateList = programStatesList;
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStateList;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws MyException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            //ProgramState programState = programStateList.get(0);
            logFile.println(programState.toString());
            logFile.close();
        }
        catch (Exception exception){
            throw new MyException("Error while printing on file");
        }
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
}
