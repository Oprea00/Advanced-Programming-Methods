package Model.value;

import Model.type.BoolType;
import Model.type.Type;

public class BoolValue implements Value {
    private Boolean value;

    BoolValue() {
        value = false;
    }

    public BoolValue(Boolean val){
        value = val;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[Boolean value "+value+"]";
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof BoolValue;
    }

    public static BoolValue ofDefault() {
        return new BoolValue(false);
    }
}
