package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_END extends TAG {
	
	public TAG_END() {
		super("");
	}
	
	public TAG_END(String name) {
		super(name);
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
	}
	
	@Override
	public byte GetId() {
		return 0x00;
	}
	
}
