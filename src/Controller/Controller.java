package Controller;

import Model.ProgramState;
import Model.adt.IStack;
import Model.exception.MyException;
import Model.statement.IStatement;
import Model.type.ReferenceType;
import Model.value.ReferenceValue;
import Model.value.Value;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Controller {
    private IRepository programStateRepository;
    private ExecutorService executor;


    public Controller(IRepository repository){
        this.programStateRepository = repository;
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap){
        return heap.entrySet()
                .stream()
                .filter(e->symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getSymbolTableAddresses(Collection<Value> symbolTableValues, Collection<Value> heap){
        //return symbolTableValues.stream()
        //        .filter(v-> v instanceof ReferenceValue)
        //        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
        //        .collect(Collectors.toList());

        return Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}),
                symbolTableValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
        )
                .collect(Collectors.toList());


    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> programStateList){
        return programStateList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    /*
    public ProgramState oneStep(ProgramState state) throws MyException{
        IStack<IStatement> stack = state.getExecutableStack();
        //displayProgramState(state);
        if(stack.isEmpty()) throw new MyException("Program State stack is empty");
        IStatement currentStmt = stack.pop();
        return currentStmt.execute(state);
    }

     */

    private void oneStepForAllPrograms(List<ProgramState> programStateList) {
        //before the execution, print the PrgState List into the log file
        programStateList.forEach(prg ->programStateRepository.logProgramStateExecution(prg));
        //RUN concurrently one step for each of the existing PrgStates
        //-----------------------------------------------------------------------
        //prepare the list of callables
        List<Callable<ProgramState>> callList = programStateList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        //start the execution of the callables
        //it returns the list of new created PrgStates (namely threads)
        try{
            List<ProgramState> newPrgList = executor.invokeAll(callList). stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch(MyException | InterruptedException | ExecutionException exception) {
                            System.out.println(exception.getMessage());
                            throw new MyException("Something went wrong during future.get..");
                        }
                    })
                    .filter(Objects::nonNull).collect(Collectors.toList());
                    //add the new created threads to the list of existing threads
                    programStateList.addAll(newPrgList);
                    //------------------------------------------------------------------------------
                    //after the execution, print the PrgState List into the log file
                    programStateList.forEach(prg ->programStateRepository.logProgramStateExecution(prg));
                    //Save the current programs in the repository
                    programStateRepository.setProgramStates(programStateList);

        } catch (InterruptedException e) {
            throw new MyException(e.getMessage());
        }
    }

    public void allSteps(){
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programStateList = removeCompletedProgram(programStateRepository.getProgramStates());
        while (programStateList.size()>0){
            oneStepForAllPrograms(programStateList);

            Collection<Value> allSymTbl = new ArrayList<>();
            programStateList.forEach(v-> allSymTbl.addAll(v.getSymbolTable().getContent().values()));
            List<Integer> symbolTableAddresses = getSymbolTableAddresses(allSymTbl, programStateList.get(0).getHeap().getContent().values());
            Map<Integer, Value> newHeap = safeGarbageCollector(symbolTableAddresses, programStateList.get(0).getHeap().getContent());
            programStateList.get(0).getHeap().setContent(newHeap);


            programStateList = removeCompletedProgram(programStateRepository.getProgramStates());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        programStateRepository.setProgramStates(programStateList);
    }

/* OLD
    public void allSteps() throws MyException{
        ProgramState programState =programStateRepository.getProgramStates().get(0);
        programStateRepository.logProgramStateExecution();
        while(! programState.getExecutableStack().isEmpty()) {
            //oneStep(programState);
            //programStateRepository.logProgramStateExecution();
            programState.getHeap().setContent(unsafeGarbageCollector(getSymbolTableAddresses(programState.getSymbolTable().getContent().values())
                    programState.getHeap().getContent().values()) , programState.getHeap().getContent()));
            programStateRepository.logProgramStateExecution();
        }
    }
    */

    public void addProgramState(ProgramState programState){
        programStateRepository.addProgramState(programState);
    }

    public void displayProgramState(ProgramState programState){
        System.out.println(programState);
    }
}
