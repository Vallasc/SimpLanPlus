package com.unibo.ci.util;

import java.util.LinkedList;

import com.unibo.ci.ast.errors.Error;

public class ErrorStorage {
    private final static LinkedList<Error> errorList = new LinkedList<>();

    public static void add(Error error){
        errorList.add(error);
    }

    public static void printAll(){
        errorList.forEach(e -> System.out.print("Error in [ " + e.row + e.col + " ]" + e.desc));
    }
}
