package com.unibo.ci.ast.types;

public class TypeFactory {
    
    public void defineType(String type){
        switch(type){
            case "INT": new TypeInt();
            case "BOOL": new TypeBool();
            case "FUNCTION": new TypeFunction();
            case "POINTER": new TypePointer(new TypeInt());
            case "VOID": new TypeVoid();
        }
    }
}
