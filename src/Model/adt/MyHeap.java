package Model.adt;

import Model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements IHeap {
    private HashMap<Integer, Value> heap = new HashMap<Integer, Value>();
    private int address = 0;

    @Override
    public boolean containsKey(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public Value getValue(Integer key) {
        return heap.get(key);
    }

    @Override
    public Integer insert(Value value) {
        address += 1;
        heap.put(address, value);
        return address;
    }

    @Override
    public void remove(Integer key) {
        heap.remove(key);
    }

    @Override
    public Map<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map<Integer, Value> newHeap) {
        heap.clear();
        for (Integer key : newHeap.keySet())
            heap.put(key, newHeap.get(key));
    }

    @Override
    public void update(Integer address, Value value) {
        heap.put(address, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer key : heap.keySet()){
            stringBuilder.append("Address: ").append(key).append(" --> Value: ").append(heap.get(key).toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
