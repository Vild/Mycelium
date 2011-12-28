package org.mycelium.mycelium.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketGetInfo extends Packet {
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
	}

	@Override
	public int getId() {
		return 0xFE;
	}
	
}
