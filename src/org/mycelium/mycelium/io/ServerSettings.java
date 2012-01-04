package org.mycelium.mycelium.io;

public class ServerSettings {
	
	private final Settings	settings;
	
	public ServerSettings() {
		settings = new Settings("Mycelium.properties");
		
		settings.getInt("port", 25565);
		settings.getString("motd", "Super 1337 server");
		settings.getString("kickmsg", "You got owned by WildN00b");
	}
	
	public int getPort() {
		return settings.getInt("port", 25565);
	}
	
	public String getMotd() {
		return settings.getString("motd", "Super 1337 server");
	}
	
	public String getKickMsg() {
		return settings.getString("kickmsg", "You got owned by WildN00b");
	}
	
}
