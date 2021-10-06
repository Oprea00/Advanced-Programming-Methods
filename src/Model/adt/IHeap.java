package Model.adt;

import Model.value.Value;

import java.util.Map;

public interface IHeap {
    boolean containsKey(Integer key);
    Value getValue(Integer key);
    Integer insert(Value value);
    void remove(Integer key);
    Map<Integer, Value> getContent();
    void setContent(Map<Integer, Value> newHeap);
    void update(Integer address, Value value);
}
