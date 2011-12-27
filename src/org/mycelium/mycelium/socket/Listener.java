package org.mycelium.mycelium.socket;

import java.io.IOException;
import java.net.ServerSocket;

import org.mycelium.mycelium.Log;

public class Listener implements Runnable {
	
	private Log				log	= Log.getLog();
	
	private final int		port;
	private ServerSocket	serversocket;
	private Thread			thread;
	
	public Listener(int port) {
		this.port = port;
		this.thread = new Thread(this, "Server Listener");
	}
	
	public void Start() {
		this.thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void Stop() {
		this.thread.stop();
	}
	
	public void WaitToEnd() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {
			log.Severe("[Mycelium] Failed to start a server listener");
			
		}
		
		log.Info("All Good :D");
		try {
			serversocket.close();
		} catch (IOException e) {
		}
		log.Info("End!");
	}
}
