package org.mycelium.mycelium;

import org.mycelium.mycelium.io.net.Listener;
import org.mycelium.mycelium.io.net.PacketHandler;

public class Main {
	
	private static Log		log	= Log.getLog();
	private static Listener	server;
	
	public static void main(String[] args) {
		log.Info("Mycelium is initializing...");
		PacketHandler.Init();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				server.Stop();
			}
		});
		
		log.Info("Starting the server...");
		server = new Listener(25565);
		
		server.Start();
		log.Info("The server is started.");
		server.WaitToEnd();
		
	}
}
