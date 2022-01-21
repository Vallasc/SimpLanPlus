package com.unibo.ci.test;

import com.unibo.ci.Main;
import com.unibo.ci.util.GlobalConfig;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		String testpath = "test/";

		// uso: test.java x, dove x è il numero del test da eseguire
		// se non viene specificato nessun numero esegui tutti i test nella testpath
		if (args.length == 0) {
			File testFolder = new File(testpath);
			for (String filename : testFolder.list()) {
				if (filename.endsWith(".slp")) {
					System.out.println("Test: " + filename);
					System.out.println("---------------------------------------");
					Main.main(new String[] { testpath.concat(filename) });
					System.out.println("---------------------------------------\n");
				}
			}
		} else {
			GlobalConfig.PRINT_COMMENTS = true;
			GlobalConfig.SHOW_MEM = true;
			GlobalConfig.MEM_SIZE = 100;
			//GlobalConfig.SHOW_DEBUG = true;
			Main.main(new String[] { testpath + "test" + args[0] + ".slp" });
		}
	}
}
