package Model.adt;

import java.util.Stack;

public class MyStack <T> implements IStack<T> {
    private Stack<T> stack = new Stack<>(); //TO DO schimb in array deque

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder stackString = new StringBuilder();
        for(T t : stack) {
            stackString.append(t).append("\n");
        }
        return stackString.toString();
    }
}
