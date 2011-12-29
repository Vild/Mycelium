package org.mycelium.mycelium.net.packet;

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
	public int getId() {
		return 0x00;
	}
	
}
