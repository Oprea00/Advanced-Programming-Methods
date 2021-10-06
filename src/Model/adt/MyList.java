package Model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList <T> implements IList <T> {
    private List<T> list = new ArrayList<>();

    @Override
    public void add(T value) {
        list.add(value);
    }

    @Override
    public T get(int position) {
        return list.get(position);
    }

    @Override
    public void printValues() {
        list.forEach(System.out::println);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T t : list) {
            stringBuilder.append(t).append("\n");
        }
        return stringBuilder.toString();
    }
}
