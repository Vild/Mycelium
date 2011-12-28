package org.mycelium.mycelium;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	
	private static Logger	log;
	private static Log		current;
	
	public Log() throws Exception {
		log = Logger.getLogger("Mycelium");
		LogFormatter format = new LogFormatter();
		
		log.setUseParentHandlers(false);
		
		FileHandler f = new FileHandler("Mycelium.log", true);
		f.setLevel(Level.ALL);
		f.setFormatter(format);
		log.addHandler(f);
		ConsoleHandler c = new ConsoleHandler();
		c.setLevel(Level.ALL);
		c.setFormatter(format);
		log.addHandler(c);
	}
	
	public static Log getLog() {
		if (current != null) return current;
		
		try {
			Log.current = new Log();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Log failed to init. Aborting!");
			System.exit(-1);
		}
		return current;
	}
	
	public void Info(String msg) {
		Log.log.log(Level.INFO, msg);
	}
	
	public void Severe(String msg) {
		Log.log.log(Level.SEVERE, msg);
	}
	
	public void Warning(String msg) {
		Log.log.log(Level.WARNING, msg);
	}
	
}
