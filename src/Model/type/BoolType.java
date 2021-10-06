package Model.type;

import Model.value.BoolValue;
import Model.value.Value;

public class BoolType implements Type {

    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }

    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public Value getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }
}
