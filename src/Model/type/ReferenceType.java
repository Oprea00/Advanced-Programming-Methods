package Model.type;

import Model.value.ReferenceValue;
import Model.value.Value;

public class ReferenceType implements Type {
   private Type innerType;

   public ReferenceType(Type inner) { innerType = inner;}

    public Type getInnerType() {
        return innerType;
    }

    @Override
    public boolean equals(Object another){
       if (another instanceof ReferenceType)
           return innerType.equals(((ReferenceType) another).getInnerType());
       else
           return false;
    }

    @Override
    public String toString(){
       return "Ref(" + innerType.toString() + ")";
    }

    @Override
    public Value getDefaultValue() {
        return new ReferenceValue(0, innerType);
    }

    @Override
    public Type deepCopy() {
        return new ReferenceType(innerType.deepCopy());
    }
}
