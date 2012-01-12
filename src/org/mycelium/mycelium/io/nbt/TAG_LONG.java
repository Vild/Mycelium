package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_LONG extends TAG {
	
	public long	Long;
	
	public TAG_LONG() {
		super("");
	}
	
	public TAG_LONG(String name) {
		super(name);
	}
	
	
	public TAG_LONG(String name, long Long) {
		super(name);
		this.Long = Long;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Long = stream.readLong();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeLong(this.Long);
	}
	
	@Override
	public byte GetId() {
		return 0x04;
	}
	
}
