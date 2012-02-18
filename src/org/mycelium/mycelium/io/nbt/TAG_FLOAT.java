package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_FLOAT extends TAG {
	
	public float	Float;
	
	public TAG_FLOAT() {
		super("");
	}
	
	public TAG_FLOAT(String name) {
		super(name);
	}
	
	
	public TAG_FLOAT(String name, float Float) {
		super(name);
		this.Float = Float;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Float = stream.readFloat();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeFloat(this.Float);
	}
	
	@Override
	public Float getData() {
		return Float;
	}
	
	@Override
	public byte getID() {
		return 0x05;
	}
	
}
