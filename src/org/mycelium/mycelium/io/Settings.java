package org.mycelium.mycelium.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.mycelium.mycelium.Log;

public class Settings {
	
	private final Properties	property;
	private final File			file;
	private final Log			log	= Log.getLog();
	
	public Settings(String path) {
		property = new Properties();
		file = new File(path);
		try {
			if (!file.exists()) file.createNewFile();
			property.load(new FileInputStream(new File("Mycelium.properties")));
		} catch (IOException e) {
			log.Info("Can't load settings file: " + file.getName());
			System.exit(-20);
		}
	}
	
	private void Save() {
		try {
			property.store(new FileOutputStream(file), "Mycelium settings file");
		} catch (IOException e) {
			log.Info("Can't save settings file: " + file.getName());
		}
	}
	
	public String getString(String key, String defaultvalue) {
		if (!this.property.containsKey(key)) {
			this.property.setProperty(key, defaultvalue);
			this.Save();
		}
		
		return this.property.getProperty(key, defaultvalue);
	}
	
	public int getInt(String key, int defaultvalue) {
		try {
			return Integer.parseInt(this.getString(key, "" + defaultvalue));
		} catch (Exception e) {
			this.property.setProperty(key, "" + defaultvalue);
			return defaultvalue;
		}
	}
	
	public boolean getBoolean(String key, boolean defaultvalue) {
		try {
			return Boolean.parseBoolean(this.getString(key, "" + defaultvalue));
		} catch (Exception e) {
			this.property.setProperty(key, "" + defaultvalue);
			return defaultvalue;
		}
	}
	
	public void setString(String key, String value) {
		this.property.setProperty(key, value);
		this.Save();
	}
	
	public void setInt(String key, int value) {
		this.property.setProperty(key, "" + value);
		this.Save();
	}
	
	public void setBoolean(String key, boolean value) {
		this.property.setProperty(key, "" + value);
		this.Save();
	}
}
