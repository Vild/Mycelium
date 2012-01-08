package org.mycelium.mycelium.io;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.mycelium.mycelium.Main;

public class Log {
	
	private static Logger	log;
	private static Log		current;
	
	public Log() throws Exception {
		log = Logger.getLogger("Mycelium");
		log.setUseParentHandlers(false);
		
		FileHandler f = new FileHandler("Mycelium.log", true);
		f.setLevel(Level.ALL);
		f.setFormatter(new LogFormatter(true));
		log.addHandler(f);
		Handler h = new Handler() {
			
			@Override
			public void publish(LogRecord arg0) {
				try {
					Main.reader.printString("\r" + this.getFormatter().format(arg0));
					Main.reader.redrawLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void flush() {
				try {
					Main.reader.flushConsole();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void close() throws SecurityException {
				
			}
		};
		h.setLevel(Level.ALL);
		h.setFormatter(new LogFormatter(false));
		log.addHandler(h);
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
		if (Main.reader != null) try {
			Main.reader.redrawLine();
		} catch (IOException e) {
		}
	}
	
	public void Severe(String msg) {
		Log.log.log(Level.SEVERE, msg);
		if (Main.reader != null) try {
			Main.reader.redrawLine();
		} catch (IOException e) {
		}
	}
	
	public void Warning(String msg) {
		Log.log.log(Level.WARNING, msg);
		if (Main.reader != null) try {
			Main.reader.redrawLine();
		} catch (IOException e) {
		}
	}
	
}
