package View;

import Controller.Controller;
import Model.ProgramState;
import Model.adt.*;
import Model.exception.MyException;
import Model.expression.*;
import Model.statement.*;
import Model.type.*;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.StringValue;
import Model.value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;


public class Interpreter {

    public static void main(String[] args) {

        //int v; v=2;Print(v)
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStack<IStatement> stack1 = new MyStack<IStatement>();
        IDictionary<String, Value> dictionary1 = new MyDictionary<String, Value>();
        IList<Value> list1 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap1 = new MyHeap();
        ex1.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState1 = new ProgramState(stack1, dictionary1, fileTable1, heap1, list1, ex1);
        IRepository repo1 = new Repository("log1.txt");
        repo1.addProgramState(programState1);
        Controller controller1 = new Controller(repo1);

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(1, new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(1, new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        IStack<IStatement> stack2 = new MyStack<IStatement>();
        IDictionary<String, Value> dictionary2 = new MyDictionary<String, Value>();
        IList<Value> list2 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap2 = new MyHeap();
        ex2.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState2 = new ProgramState(stack2, dictionary2, fileTable2, heap2, list2, ex2);
        IRepository repo2 = new Repository("log2.txt");
        repo2.addProgramState(programState2);
        Controller controller2 = new Controller(repo2);


        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        IStack<IStatement> stack3 = new MyStack<IStatement>();
        IDictionary<String, Value> dictionary3 = new MyDictionary<String, Value>();
        IList<Value> list3 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap3 = new MyHeap();
        ex3.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState3 = new ProgramState(stack3, dictionary3, fileTable3, heap3, list3, ex3);
        IRepository repo3 = new Repository("log3.txt");
        repo3.addProgramState(programState3);
        Controller controller3 = new Controller(repo3);


        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CloseReadFileStatement(new VariableExpression("varf"))))))))));
        IStack<IStatement> stack4 = new MyStack<IStatement>();
        IDictionary<String, Value> dictionary4 = new MyDictionary<String, Value>();
        IList<Value> list4 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap4 = new MyHeap();
        ex4.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState4 = new ProgramState(stack4, dictionary4, fileTable4, heap4, list4, ex4);
        IRepository repo4 = new Repository("log4.txt");
        repo4.addProgramState(programState4);
        Controller controller4 = new Controller(repo4);


        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(1, new ReadHeap(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        IStack<IStatement> stack5 = new MyStack<>();
        IDictionary<String, Value> dictionary5 = new MyDictionary<>();
        IList<Value> list5 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap5 = new MyHeap();
        ex5.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState5 = new ProgramState(stack5, dictionary5, fileTable5, heap5, list5, ex5);
        IRepository repo5 = new Repository("log5.txt");
        repo5.addProgramState(programState5);
        Controller controller5 = new Controller(repo5);


        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));
        IStack<IStatement> stack6 = new MyStack<>();
        IDictionary<String, Value> dictionary6 = new MyDictionary<>();
        IList<Value> list6 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap6 = new MyHeap();
        ex6.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState6 = new ProgramState(stack6, dictionary6, fileTable6, heap6, list6, ex6);
        IRepository repo6 = new Repository("log6.txt");
        repo6.addProgramState(programState6);
        Controller controller6 = new Controller(repo6);


        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(5, new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(2,new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        IStack<IStatement> stack7 = new MyStack<>();
        IDictionary<String, Value> dictionary7 = new MyDictionary<>();
        IList<Value> list7 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap7 = new MyHeap();
        ex7.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState7 = new ProgramState(stack7, dictionary7, fileTable7, heap7, list7, ex7);
        IRepository repo7 = new Repository("log7.txt");
        repo7.addProgramState(programState7);
        Controller controller7 = new Controller(repo7);


        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement
                                                (new WriteHeapStatement("a", new ValueExpression(new IntValue(30))), new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a")))))))));
        IStack<IStatement> stack8 = new MyStack<>();
        IDictionary<String, Value> dictionary8 = new MyDictionary<>();
        IList<Value> list8 = new MyList<Value>();
        IDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<StringValue, BufferedReader>();
        IHeap heap8 = new MyHeap();
        ex8.typeCheck(new MyDictionary<String, Type>());
        ProgramState programState8 = new ProgramState(stack8, dictionary8, fileTable8, heap8, list8, ex8);
        IRepository repo8 = new Repository("log8.txt");
        repo8.addProgramState(programState8);
        Controller controller8 = new Controller(repo8);


//------------------------------------type check example errors---------------------------------------------------------

        //assign error, int v = "error"
        try{
            IStatement ex1error = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignStatement("v", new ValueExpression(new StringValue("error"))), new PrintStatement(new VariableExpression("v"))));
            ex1error.typeCheck(new MyDictionary<String, Type>());
        }
        catch (MyException exception){
            System.out.println("Type Check error at ex1error statement. " + exception.getMessage());
        }

        //arithmetic expression error, b=a+"error"
        try{
            IStatement ex2error = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                            new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(1, new ValueExpression(new IntValue(2)), new
                                    ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                    new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(1, new VariableExpression("a"), new
                                            ValueExpression(new StringValue("error")))), new PrintStatement(new VariableExpression("b"))))));
            ex2error.typeCheck(new MyDictionary<String, Type>());
        }
        catch (MyException exception){
            System.out.println("Type Check error at ex2error statement. " + exception.getMessage());
        }

        //relational expression error, v > true
        try{
            IStatement ex7error = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                            new CompoundStatement(new WhileStatement(new RelationalExpression(5, new VariableExpression("v"), new ValueExpression(new BoolValue(true))),
                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(2,new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                    new PrintStatement(new VariableExpression("v")))));
            ex7error.typeCheck(new MyDictionary<String, Type>());
        }
        catch (MyException exception){
            System.out.println("Type Check error at ex7error statement. " + exception.getMessage());
        }



        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        menu.show();
    }
}
