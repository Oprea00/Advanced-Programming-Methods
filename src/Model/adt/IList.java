package Model.adt;

public interface IList <T> {
    void add(T value);
    T get(int position);
    void printValues();
}
