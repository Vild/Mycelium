package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_COMPOUND extends TAG {
	
	public TAG_COMPOUND() {
		super("");
	}
	
	public TAG_COMPOUND(String name) {
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
		return 0x10;
	}
	
}
