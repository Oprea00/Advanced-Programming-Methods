package Model.type;

import Model.value.IntValue;
import Model.value.Value;

public class IntType implements Type {

    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Value getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }
}
