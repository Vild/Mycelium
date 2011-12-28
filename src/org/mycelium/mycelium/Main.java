package org.mycelium.mycelium;

import org.mycelium.mycelium.net.Listener;
import org.mycelium.mycelium.net.PacketHandler;

public class Main {
	
	private static Log	log	= Log.getLog();
	
	public static void main(String[] args) {
		log.Info("HI");
		PacketHandler.Init();
		
		Listener server = new Listener(25565);
		server.Start();
		server.WaitToEnd();
		server.Stop();
	}
	
}
