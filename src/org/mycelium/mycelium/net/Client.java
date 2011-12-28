package org.mycelium.mycelium.net;

import java.io.IOException;
import java.net.Socket;

import org.mycelium.mycelium.Log;
import org.mycelium.mycelium.net.packet.Packet;
import org.mycelium.mycelium.net.packet.PacketGetInfo;
import org.mycelium.mycelium.net.packet.PacketKickDisconnect;
import org.mycelium.mycelium.net.packet.PacketHandshake;
import org.mycelium.mycelium.net.packet.PacketLogin;

public class Client {
	
	private final Socket	socket;
	private final Log		log	= Log.getLog();
	
	public boolean IsDead = false;
	
	public Client(Socket socket) {
		this.socket = socket;
		this.Connected();
	}
	
	public void Connected() {
		try {
			log.Info("Client connected");
			Packet tmp = PacketHandler.GetPacket(socket);
			
			if (tmp instanceof PacketGetInfo) {
				String s = "TROLOLOLO" + "\u00A7" + "0" + "\u00A7" + "10";
				PacketKickDisconnect a = new PacketKickDisconnect();
				a.Reason = s;
				PacketHandler.SendPacket(socket, a);
				
				socket.close();
				IsDead = true;
				return;
			}
			
			if (!(tmp instanceof PacketHandshake)) socket.close();
			PacketHandshake a = (PacketHandshake) tmp;
			
			log.Info("Usernamn:" + a.Username);
			a.Hash = "-"; // "2e66f1dc032ab5f0";
			PacketHandler.SendPacket(socket, a);
			
			tmp = PacketHandler.GetPacket(socket);
			
			if (!(tmp instanceof PacketLogin)) socket.close();
			
			PacketLogin b = (PacketLogin) tmp;
			log.Info("Protocal v." + b.ProtocalVersion + " Usernamn: " + b.Username);
			
			PacketKickDisconnect c = new PacketKickDisconnect();
			c.Reason = "WORKING :D";
			PacketHandler.SendPacket(socket, c);
			IsDead = true;
		} catch (IOException e) {
			log.Severe(e.getMessage());
			try {
				socket.close();
			} catch (IOException e1) {
			}
		}
	}
}
