package org.mycelium.mycelium.io.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketGetInfo extends Packet {
	
	public PacketGetInfo() {
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
	}
	
	@Override
	public byte getId() {
		return (byte) 0xFE;
	}
	
}
