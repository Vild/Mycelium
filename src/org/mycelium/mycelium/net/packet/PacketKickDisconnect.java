package org.mycelium.mycelium.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketKickDisconnect extends Packet {
	
	public String	Reason;
	
	public PacketKickDisconnect() {}
	public PacketKickDisconnect(String Reason) {
		this.Reason = Reason;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Reason = ReadString(stream, 100);
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		WriteString(stream, this.Reason);
	}
	
	@Override
	public int getId() {
		return 0xFF;
	}
	
}
