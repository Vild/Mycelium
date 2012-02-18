package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_SHORT extends TAG {
	
	public short	Short;
	
	public TAG_SHORT() {
		super("");
	}
	
	public TAG_SHORT(String name) {
		super(name);
	}
	
	
	public TAG_SHORT(String name, short Short) {
		super(name);
		this.Short = Short;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Short = stream.readShort();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeShort(this.Short);
	}
	
	@Override
	public byte GetId() {
		return 0x02;
	}
	
}
