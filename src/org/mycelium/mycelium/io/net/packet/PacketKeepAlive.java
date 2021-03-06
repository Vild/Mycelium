package org.mycelium.mycelium.io.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketKeepAlive extends Packet {
	
	public int	KeepAliveId;
	
	public PacketKeepAlive() {}
	public PacketKeepAlive(int KeepAliveId) {
		this.KeepAliveId = KeepAliveId;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.KeepAliveId = stream.readInt();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeInt(KeepAliveId);
	}
	
	@Override
	public byte getId() {
		return (byte) 0x00;
	}
	
}
