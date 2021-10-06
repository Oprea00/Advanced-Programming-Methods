package Model.type;

import Model.value.StringValue;
import Model.value.Value;

public class StringType implements Type {

    @Override
    public Value getDefaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public String toString(){
        return "String";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }
}
