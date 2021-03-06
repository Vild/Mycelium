package org.mycelium.mycelium.io.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import org.mycelium.mycelium.Main;
import org.mycelium.mycelium.io.Log;
import org.mycelium.mycelium.io.net.packet.Packet;
import org.mycelium.mycelium.io.net.packet.PacketGetInfo;
import org.mycelium.mycelium.io.net.packet.PacketHandshake;
import org.mycelium.mycelium.io.net.packet.PacketKeepAlive;
import org.mycelium.mycelium.io.net.packet.PacketKickDisconnect;
import org.mycelium.mycelium.io.net.packet.PacketLogin;

public class Client extends PacketReceiver {
	
	public Log			log	= Log.getLog();
	public Socket		socket;
	public Random		random;
	public int			KeepAlive_Hash;
	
	private waitPacket	WaitPacket;
	
	class waitPacket extends Thread {
		
		private final Client	client;
		
		public waitPacket(Client client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					client.HandlePacket(PacketHandler.GetPacket(client.socket));
				} catch (IOException e) {
				}
				
			}
		}
	}
	
	public Client(Socket socket, Random random) {
		this.socket = socket;
		this.random = random;
		this.WaitPacket = new waitPacket(this);
		this.WaitPacket.start();
	}
	
	public void SendPacket(Packet packet) throws IOException {
		PacketHandler.SendPacket(socket, packet);
	}
	
	public Packet GetPacket() throws IOException {
		return PacketHandler.GetPacket(socket);
	}
	
	public void Kick(String msg) {
		// this.KeepAlive.interrupt();
		this.WaitPacket.interrupt();
		
		try {
			SendPacket(new PacketKickDisconnect(msg));
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
		// this.KeepAlive.interrupt();
		this.WaitPacket.interrupt();
		
		try {
			this.socket.close();
		} catch (IOException e) {
		}
	}
	
	@Override
	public void RecivedPacket(PacketKeepAlive packet) {
		// TODO: Implement KeepAlive
		if (packet.KeepAliveId == KeepAlive_Hash) KeepAlive_Hash = -1;
	}
	
	@Override
	public void RecivedPacket(PacketLogin packet) {
		this.log.Info("Protocal v." + packet.ProtocalVersion + " Username: " + packet.Username + " Kick msg: " + Main.serverSettings.getKickMsg());
		Kick(Main.serverSettings.getKickMsg());
		return;
		// this.KeepAlive.start();
	}
	
	@Override
	public void RecivedPacket(PacketHandshake packet) {
		this.log.Info(packet.Username + " connected to the server");
		try {
			SendPacket(new PacketHandshake("-"));// Integer.toHexString(random.nextInt())));
			this.log.Info("Sent Handshake");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void RecivedPacket(PacketGetInfo packet) {
		log.Info("Sending info about the server");
		// Kick("MOTD" + "\u00A7" + "0" + "\u00A7" + "10");
		try {
			SendPacket(new PacketKickDisconnect(Main.serverSettings.getMotd() + "�1337�31337"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void RecivedPacket(PacketKickDisconnect packet) {
		this.Close();
	}
	
}
