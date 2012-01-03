package org.mycelium.mycelium;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
	
	private boolean longdate;
	
	public LogFormatter(boolean longdate) {
		this.longdate = longdate;
	}
	
	@Override
	public String format(LogRecord record) {
		StringBuffer buf = new StringBuffer();
		
		Date date = new Date();
		
		if (longdate)
			buf.append(date);
		else
			buf.append(new SimpleDateFormat("hh:mm:ss").format(date));
		
		buf.append(" [");
		buf.append(record.getLevel());
		buf.append("] ");
		buf.append(formatMessage(record));
		buf.append('\n');
		return buf.toString();
	}
	
}