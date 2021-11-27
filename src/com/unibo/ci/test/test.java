package com.unibo.ci.test;

import com.unibo.ci.main.Main;
import java.io.File;
import java.io.FilenameFilter;

public class test {
    
    public static void main(String[] args){
    	String testpath = "test/"; 
    	
    	//uso: test.java x, dove x è il numero del test da eseguire
    	//se non viene specificato nessun numero esegui tutti i test nella testpath
    	if (args.length == 0) {
	    	File testFolder = new File(testpath);
	    	for(String filename : testFolder.list()) {
	    		if (filename.endsWith(".slp") && filename.startsWith("test")) {
	    			System.out.println(filename);
	    			Main.main(new String[]{testpath.concat(filename)});
	    		}
	    	}
    	} else {
    		 
    		Main.main(new String[]{testpath + "test" + args[1] + ".slp"});
    	}
    	
        /*for (int i = 0; i <= 8; i++){
            System.out.println("Test " + i);
            Main.main(new String[]{"test/test" + i + ".slp"});
            System.out.println("Fine Test " + i + "\n");
        }*/
    }
}
