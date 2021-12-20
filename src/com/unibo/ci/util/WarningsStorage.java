package com.unibo.ci.util;

import java.util.LinkedList;

import com.unibo.ci.ast.errors.Error;
import com.unibo.ci.ast.errors.Warning;

public class WarningsStorage {
    private final static LinkedList<Warning> warningList = new LinkedList<>();

    public static void add(Warning warning) {
        warningList.add(warning);
    }

    public static void printAll() {
        warningList.forEach(e -> System.out.print("⚠️ Warning in [" + e.row + ", " + e.col + "]: " + e.desc));
    }

    public static LinkedList<Warning> getWarningList() {
        return warningList;
    }

    public static void clear() {
    }

}
