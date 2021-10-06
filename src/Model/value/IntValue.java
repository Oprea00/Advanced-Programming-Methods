package Model.value;

import Model.type.IntType;
import Model.type.Type;

import java.util.Objects;

public class IntValue implements Value {
    private int value;

    IntValue() {
        this.value = 0;
    }

    public IntValue(int val){
        value = val;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "[Integer value "+value+"]";
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntValue;
    }

    public static IntValue ofDefault() {
        return new IntValue(0);
    }
}
