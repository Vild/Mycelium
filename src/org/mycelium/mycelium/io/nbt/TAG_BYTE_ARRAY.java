package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_BYTE_ARRAY extends TAG {
	
	public byte[]	Byte;
	
	public TAG_BYTE_ARRAY() {
		super("");
	}
	
	public TAG_BYTE_ARRAY(String name) {
		super(name);
	}
	
	public TAG_BYTE_ARRAY(String name, byte[] Byte) {
		super(name);
		this.Byte = Byte;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Byte = new byte[stream.readInt()];
		stream.readFully(this.Byte);
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeInt(this.Byte.length);
		stream.write(this.Byte);
	}
	
	@Override
	public byte GetId() {
		return 0x07;
	}
	
}
