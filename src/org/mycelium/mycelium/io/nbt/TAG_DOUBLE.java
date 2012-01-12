package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_DOUBLE extends TAG {
	
	public double	Double;
	
	public TAG_DOUBLE() {
		super("");
	}
	
	public TAG_DOUBLE(String name) {
		super(name);
	}
	
	
	public TAG_DOUBLE(String name, double Double) {
		super(name);
		this.Double = Double;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.Double = stream.readDouble();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeDouble(this.Double);
	}
	
	@Override
	public byte GetId() {
		return 0x06;
	}
	
}
