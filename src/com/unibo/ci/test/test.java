package com.unibo.ci.test;

import com.unibo.ci.main.Main;

public class test {
    
    public static void main(String[] args){
        for (int i = 0; i <= 8; i++){
            System.out.println("Test " + i);
            Main.main(new String[]{"test/test" + i + ".slp"});
            System.out.println("Fine Test " + i + "\n");
        }
    }
}
