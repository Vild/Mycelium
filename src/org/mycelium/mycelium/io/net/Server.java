package org.mycelium.mycelium.io.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

import org.mycelium.mycelium.io.Log;

public class Server implements Runnable {
	
	private Log								log		= Log.getLog();
	
	private final int						port;
	private ServerSocket					server;
	private Thread							thread;
	
	public final static ArrayList<Client>	Clients	= new ArrayList<Client>();
	
	public Server(int port) {
		this.port = port;
		this.thread = new Thread(this, "Server Listener");
	}
	
	public void Start() {
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(1000);
			this.thread.start();
			log.Info("The server is started on port " + port + ".");
		} catch (IOException e) {
			log.Severe("[Mycelium] Failed to start a server listener");
			e.printStackTrace();
			
		}
		
	}
	
	public void Stop() {
		try {
			server.close();
		} catch (IOException e) {
		}
	}
	
	public void WaitToEnd() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean IsRunning() {
		return this.thread != null && this.thread.isAlive() && !this.thread.isInterrupted();
	}
	
	@Override
	public void run() {
		while (true) {
			if (server == null || server.isClosed()) return;
			
			try {
				Socket client = server.accept();
				Clients.add(new Client(client, new Random(System.nanoTime())));
			} catch (IOException e) {
			}
		}
	}
}
