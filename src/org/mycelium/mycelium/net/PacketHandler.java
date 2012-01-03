package org.mycelium.mycelium.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.mycelium.mycelium.Log;
import org.mycelium.mycelium.net.packet.Packet;
import org.mycelium.mycelium.net.packet.PacketGetInfo;
import org.mycelium.mycelium.net.packet.PacketKickDisconnect;
import org.mycelium.mycelium.net.packet.PacketHandshake;
import org.mycelium.mycelium.net.packet.PacketKeepAlive;
import org.mycelium.mycelium.net.packet.PacketLogin;

public class PacketHandler {
	
	private static final Log				log	= Log.getLog();
	
	public static HashMap<Byte, Class<?>>	Packets;
	
	public static void Init() {
		Packets = new HashMap<Byte, Class<?>>();
		log.Info("REG");
		Packets.put((byte) 0x00, PacketKeepAlive.class);
		Packets.put((byte) 0x01, PacketLogin.class);
		Packets.put((byte) 0x02, PacketHandshake.class);
		Packets.put((byte) 0xFE, PacketGetInfo.class);
		Packets.put((byte) 0xFF, PacketKickDisconnect.class);
		log.Info("DONE");
	}
	
	public static Packet GetPacket(Socket socket) throws IOException {
		DataInputStream input = new DataInputStream(socket.getInputStream());
		//int id = input.readUnsignedByte();
		byte id = input.readByte();
		if (!Packets.containsKey(id)) {
			log.Severe("UNKNOWN PACKET ID:" + id);
			return null;
		}
		try {
			Packet a = (Packet) Packets.get(id).newInstance();
			a.Read(input);
			return a;
		} catch (Exception e) {
			log.Severe(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static void SendPacket(Socket socket, Packet packet) throws IOException {
		if (!Packets.containsKey(packet.getId())) {
			log.Severe("UNKNOWN PACKET ID:" + packet.getId());
			return;
		}
		
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeByte(packet.getId());
		packet.Write(output);
		output.flush();
	}
	
}
