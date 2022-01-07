package com.unibo.ci.util;

import java.util.LinkedList;
import java.util.logging.Logger;

import com.unibo.ci.ast.errors.Error;
import com.unibo.ci.ast.errors.Warning;

public class WarningsStorage {
    private static Logger LOGGER = Logger.getLogger(WarningsStorage.class.getCanonicalName());
    private final static LinkedList<Warning> warningList = new LinkedList<>();

    public WarningsStorage(Logger logger) {
        LOGGER = logger;
    }

    public static void add(Warning warning) {
        warningList.add(warning);
    }

    public static void printAll() {
        warningList.forEach(e -> LOGGER.warning("⚠️ Warning in [" + e.row + ", " + e.col + "]: " + e.desc));
    }

    public static LinkedList<Warning> getWarningList() {
        return warningList;
    }

    public static void clear() {
        // TODO Auto-generated method stub
        warningList.clear();

    }

}
