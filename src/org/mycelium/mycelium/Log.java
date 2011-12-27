package org.mycelium.mycelium;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Log {
	
	public class LogFormatter extends Formatter {
		
		@Override
		public String format(LogRecord record) {
			StringBuffer buf = new StringBuffer();
			buf.append(new java.util.Date());
			buf.append(" [");
			buf.append(record.getLevel());
			buf.append("] ");
			buf.append(formatMessage(record));
			buf.append('\n');
			return buf.toString();
		}
		
	}
	
	private static Logger	log;
	
	public static void Init() throws Exception {
		log = Logger.getLogger("Mycelium");
		LogFormatter format = new Log().new LogFormatter();
		
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
	
	public static Logger getLog() {
		if (log != null) return log;
		
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Log failed to init. Aborting!");
			System.exit(-1);
		}
		return log;
	}
}
