package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_BYTE extends TAG {
	
	public byte	Byte;
	
	public TAG_BYTE() {
		super("");
	}
	
	public TAG_BYTE(String name) {
		super(name);
	}
	
	public TAG_BYTE(String name, byte Byte) {
		super(name);
		this.Byte = Byte;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Byte = stream.readByte();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeByte(this.Byte);
	}
	
	@Override
	public byte GetId() {
		return 0x01;
	}
	
}
