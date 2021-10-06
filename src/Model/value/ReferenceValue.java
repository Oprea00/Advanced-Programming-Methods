package Model.value;

import Model.type.ReferenceType;
import Model.type.Type;

import java.text.MessageFormat;

public class ReferenceValue implements Value {
    private int address;
    private Type locationType;

    public ReferenceValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address){
        this.address = address;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public String toString(){
        return MessageFormat.format("[Reference address={0}, type={1}]", address, locationType);
    }
}
