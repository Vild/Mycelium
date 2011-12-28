package org.mycelium.mycelium.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketHandshake extends Packet {
	
	public String	Username;
	public String	Hash;		// - no auth, + pass not working, random
								
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Username = ReadString(stream, 32);
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		WriteString(stream, Hash);
	}
	
	@Override
	public int getId() {
		return 0x02;
	}
	
}
