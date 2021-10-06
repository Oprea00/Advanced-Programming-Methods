package Model.adt;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private Hashtable<K, V> dictionary;// = new Hashtable<>();

    public MyDictionary(){
        dictionary = new Hashtable<>();
    }

    public MyDictionary(Hashtable<K,V> hashtable) {
        dictionary = hashtable;
    }

    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public V getValue(K key) {
        return dictionary.get(key);
    }

    @Override
    public void add(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V remove(K key) {
        return dictionary.remove(key);
    }

    @Override
    public Map<K, V> getContent() {
        return dictionary;
    }

    @Override
    public IDictionary<K, V> deepCopy() {
        return new MyDictionary((Hashtable<K, V>) this.dictionary.clone());
    }

    @Override
   public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       for(K key : dictionary.keySet()){
           stringBuilder.append("Key: ").append(key).append(" --> Value: ").append(dictionary.get(key).toString()).append("\n");
       }
       return stringBuilder.toString();
   }
}
