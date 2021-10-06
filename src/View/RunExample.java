package View;

import Controller.Controller;
import Model.exception.MyException;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String description ,Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allSteps();
        }
        catch (MyException exception){
            System.out.println("Error while running the program. Message:" + exception.getMessage());
        }
    }
}
