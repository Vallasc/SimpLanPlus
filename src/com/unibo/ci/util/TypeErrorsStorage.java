package com.unibo.ci.util;

import java.util.LinkedList;

import com.unibo.ci.ast.errors.Error;

public class TypeErrorsStorage {
    private final static LinkedList<Error> errorList = new LinkedList<>();

    public static void add(Error error){
        errorList.add(error);
    }

    public static void printAll(){
        errorList.forEach(e -> System.out.print("Type error in [ " + e.row + e.col + " ]" + e.desc));
    }

    public static LinkedList<Error> getErrorList(){ 
        return errorList; 
    }
}
