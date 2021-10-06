package Model.adt;

public interface IStack<T> {
    T pop();
    void push(T value);
    Boolean isEmpty();
    String toString();
}
