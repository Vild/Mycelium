package org.mycelium.mycelium.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import org.mycelium.mycelium.Log;

public class Listener implements Runnable {
	
	private Log					log	= Log.getLog();
	
	private final int			port;
	private ServerSocket		server;
	private Thread				thread;
	
	public ArrayList<Client>	Clients;
	
	public Listener(int port) {
		this.port = port;
		this.thread = new Thread(this, "Server Listener");
		this.Clients = new ArrayList<Client>();
	}
	
	public void Start() {
		try {
			server = new ServerSocket(port);
			this.thread.start();
		} catch (IOException e) {
			log.Severe("[Mycelium] Failed to start a server listener");
			e.printStackTrace();
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void Stop() {
		this.thread.stop();
		try {
			server.close();
		} catch (IOException e) {}
	}
	
	public void WaitToEnd() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket client = server.accept();
				Clients.add(new Client(client, new Random(System.nanoTime()))); 
			} catch (IOException e) {}
		}
	}
}