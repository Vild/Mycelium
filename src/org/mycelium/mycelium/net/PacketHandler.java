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
	
	private static final Log								log		= Log.getLog();
	
	public static HashMap<Integer, Class<?>>	packets;
	
	public static void Init() {
		packets = new HashMap<Integer, Class<?>>(4);
		log.Info("REG");
		packets.put(0x00, PacketKeepAlive.class);
		packets.put(0x01, PacketLogin.class);
		packets.put(0x02, PacketHandshake.class);
		packets.put(0xFE, PacketGetInfo.class);
		packets.put(0xFF, PacketKickDisconnect.class);
		log.Info("DONE");
	}
	
	public static Packet GetPacket(Socket socket) throws IOException {
		DataInputStream input = new DataInputStream(socket.getInputStream());
		int id = input.readUnsignedByte();
		if (!packets.containsKey(id)) {
			log.Severe("UNKNOWN PACKET ID:" + id);
			return null;
		}
		try {
			Packet a = (Packet) packets.get(id).newInstance();
			a.Read(input);
			return a;
		} catch (Exception e) {
			log.Severe(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static void SendPacket(Socket socket, Packet packet) throws IOException {
		if (!packets.containsKey(packet.getId())){
			log.Severe("UNKNOWN PACKET ID:" + packet.getId());
			return;
		}
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeInt(packet.getId());
		packet.Write(output);
	}
	
}
