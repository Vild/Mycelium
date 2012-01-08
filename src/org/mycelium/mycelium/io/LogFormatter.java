package org.mycelium.mycelium.io;

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
		
		Date date = new Date(record.getMillis());
		
		if (longdate)
			buf.append(date);
		else
			buf.append(new SimpleDateFormat("HH:mm:ss").format(date));
		
		buf.append(" [");
		buf.append(record.getLevel().getLocalizedName().toUpperCase());
		buf.append("] ");
		buf.append(formatMessage(record));
		buf.append('\n');
		return buf.toString();
	}
	
}