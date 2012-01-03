package org.mycelium.mycelium.io.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketHandshake extends Packet {
	
	public String	Username;
	public String	Hash;		// - no auth, + pass not working, random
								
	public PacketHandshake() {
	}
	
	public PacketHandshake(String Hash) {
		this.Hash = Hash;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Username = ReadString(stream, 32);
		
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		WriteString(stream, Hash);
	}
	
	@Override
	public byte getId() {
		return (byte) 0x02;
	}
	
}
