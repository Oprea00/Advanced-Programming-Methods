package Model.value;

import Model.type.StringType;
import Model.type.Type;

public class StringValue implements Value {
    private String value;

    public StringValue(String val){
        value = val;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof StringValue;
    }

    public static StringValue ofDefault() {
        return new StringValue("");
    }
}
