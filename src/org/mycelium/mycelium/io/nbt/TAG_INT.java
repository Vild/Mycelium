package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_INT extends TAG {
	
	public int	Int;
	
	public TAG_INT() {
		super("");
	}
	
	public TAG_INT(String name) {
		super(name);
	}
	
	
	public TAG_INT(String name, int Int) {
		super(name);
		this.Int = Int;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Int = stream.readInt();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeInt(this.Int);
	}
	
	@Override
	public byte GetId() {
		return 0x03;
	}
	
}