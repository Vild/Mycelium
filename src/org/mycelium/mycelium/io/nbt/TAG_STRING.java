package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_STRING extends TAG {
	
	public String	String;
	
	public TAG_STRING() {
		super("");
	}
	
	public TAG_STRING(String name) {
		super(name);
	}
	
	
	public TAG_STRING(String name, String String) {
		super(name);
		this.String = String;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.String = stream.readUTF();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeUTF(this.String);
	}
	
	@Override
	public byte GetId() {
		return 0x08;
	}
	
}
