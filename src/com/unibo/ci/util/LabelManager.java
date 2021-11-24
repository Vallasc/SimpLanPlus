package com.unibo.ci.util;

public class LabelManager {
    // Singleton
    private static LabelManager instance = null;

    private static int labelCount = 0;

    public static LabelManager getInstance() {
        if(instance==null)
            instance = new LabelManager();
        return instance;
    }

    /**
     * Genera una nuova label
     *
     * @param key chiave della label
     * @return label univoca
     */
    public String newLabel(String key) {
        labelCount += 1;
        return key + labelCount;
    }
}
