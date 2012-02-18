package org.mycelium.mycelium.io.net;

import org.mycelium.mycelium.io.net.packet.Packet;
import org.mycelium.mycelium.io.net.packet.PacketGetInfo;
import org.mycelium.mycelium.io.net.packet.PacketHandshake;
import org.mycelium.mycelium.io.net.packet.PacketKeepAlive;
import org.mycelium.mycelium.io.net.packet.PacketKickDisconnect;
import org.mycelium.mycelium.io.net.packet.PacketLogin;

public class PacketReceiver {
	
	public void HandlePacket(Packet packet) {
		if (packet instanceof PacketKeepAlive)
			RecivedPacket((PacketKeepAlive) packet);
		else if (packet instanceof PacketLogin)
			RecivedPacket((PacketLogin) packet);
		else if (packet instanceof PacketHandshake)
			RecivedPacket((PacketHandshake) packet);
		else if (packet instanceof PacketGetInfo)
			RecivedPacket((PacketGetInfo) packet);
		else if (packet instanceof PacketKickDisconnect)
			RecivedPacket((PacketKickDisconnect) packet);
		else
			RecivedPacket(packet);
	}
	
	public void RecivedPacket(Packet packet) {
	}
	
	public void RecivedPacket(PacketKeepAlive packet) {
		RecivedPacket((Packet) packet);
	}
	
	public void RecivedPacket(PacketLogin packet) {
		RecivedPacket((Packet) packet);
	}
	
	public void RecivedPacket(PacketHandshake packet) {
		RecivedPacket((Packet) packet);
	}
	
	public void RecivedPacket(PacketGetInfo packet) {
		RecivedPacket((Packet) packet);
	}
	
	public void RecivedPacket(PacketKickDisconnect packet) {
		RecivedPacket((Packet) packet);
	}
}
