package org.mycelium.mycelium.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import org.mycelium.mycelium.Log;
import org.mycelium.mycelium.net.packet.Packet;
import org.mycelium.mycelium.net.packet.PacketGetInfo;
import org.mycelium.mycelium.net.packet.PacketHandshake;
import org.mycelium.mycelium.net.packet.PacketKeepAlive;
import org.mycelium.mycelium.net.packet.PacketKickDisconnect;
import org.mycelium.mycelium.net.packet.PacketLogin;

public class Client {
	
	public Log			log	= Log.getLog();
	public Socket		socket;
	public Random		random;
	
	private keepAlive	KeepAlive;
	private waitPacket	WaitPacket;
	
	class keepAlive extends Thread {
		
		private final Client	client;
		public int				Hash	= -1;
		
		public keepAlive(Client client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(55000);
			} catch (InterruptedException e) {
			}
			Hash = client.random.nextInt();
			try {
				PacketHandler.SendPacket(client.socket, new PacketKeepAlive(Hash));
			} catch (IOException e) {
			}
			
			int i = 0;
			while (Hash != -1) {
				if (i > 6000) {
					client.Kick("Didn't answer on KeepAlive Packet");
					return;
				}
				
				try {
					sleep(10);
				} catch (InterruptedException e) {
				}
				i++;
			}
		}
		
	}
	
	class waitPacket extends Thread {
		
		private final Client	client;
		
		public waitPacket(Client client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					RecivedPacket(PacketHandler.GetPacket(client.socket));
				} catch (IOException e) {
				}
				
			}
		}
		
		private void RecivedPacket(Packet packet) {
			client.log.Info(packet.toString());
			if (packet instanceof PacketKeepAlive)
				client.RecivedPacket((PacketKeepAlive) packet);
			else if (packet instanceof PacketLogin)
				client.RecivedPacket((PacketLogin) packet);
			else if (packet instanceof PacketHandshake)
				client.RecivedPacket((PacketHandshake) packet);
			else if (packet instanceof PacketGetInfo)
				client.RecivedPacket((PacketGetInfo) packet);
			else if (packet instanceof PacketKickDisconnect)
				client.RecivedPacket((PacketKickDisconnect) packet);
		}
	}
	
	public Client(Socket socket, Random random) {
		this.socket = socket;
		this.random = random;
		this.KeepAlive = new keepAlive(this);
		this.WaitPacket = new waitPacket(this);
		this.WaitPacket.start();
	}
	
	public void Kick(String msg) {
		//this.KeepAlive.interrupt();
		this.WaitPacket.interrupt();
		
		try {
			PacketHandler.SendPacket(socket, new PacketKickDisconnect(msg));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	public void Close() {
		//this.KeepAlive.interrupt();
		this.WaitPacket.interrupt();
		
		try {
			this.socket.close();
		} catch (IOException e) {
		}
	}
	
	private boolean Connected() {
		return this.socket.isConnected();
	}
	
	public void RecivedPacket(PacketKeepAlive packet) {
		if (packet.KeepAliveId == KeepAlive.Hash) KeepAlive.Hash = -1;
	}
	
	public void RecivedPacket(PacketLogin packet) {
		this.log.Info("Protocal v." + packet.ProtocalVersion + " Username: " + packet.Username);
		Kick("WORKING :D");
		return;
//		this.KeepAlive.start();
	}
	
	public void RecivedPacket(PacketHandshake packet) {
		this.log.Info(packet.Username + " connected to the server");
		try {
			PacketHandler.SendPacket(socket, new PacketHandshake("-"));//Integer.toHexString(random.nextInt())));
			this.log.Info("Sendt Handshake");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RecivedPacket(PacketGetInfo packet) {
		log.Info("Sending info about the server");
		Kick("MOTD" + "\u00A7" + "0" + "\u00A7" + "10");
	}
	
	public void RecivedPacket(PacketKickDisconnect packet) {
		this.Close();
	}
	
}
