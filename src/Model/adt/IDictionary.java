package Model.adt;

import java.util.Map;

public interface IDictionary <K, V> {
    boolean containsKey(K key);
    V getValue(K key);
    void add(K key, V value);
    V remove(K key);
    Map<K, V> getContent();
    IDictionary<K, V> deepCopy();
}
