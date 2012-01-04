package org.mycelium.mycelium;

import org.mycelium.mycelium.io.ServerSettings;
import org.mycelium.mycelium.io.net.Listener;
import org.mycelium.mycelium.io.net.PacketHandler;

public class Main {
	
	private static Log				log	= Log.getLog();
	private static Listener			server;
	public static ServerSettings	serverSettings;
	
	public static void main(String[] args) {
		log.Info("Mycelium is initializing...");
		PacketHandler.Init();
		serverSettings = new ServerSettings();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				server.Stop();
			}
		});
		
		log.Info("Starting the server...");
		server = new Listener(serverSettings.getPort());
		server.Start();
		
		
	}
}
