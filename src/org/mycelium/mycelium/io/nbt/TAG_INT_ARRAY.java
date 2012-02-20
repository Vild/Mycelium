package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TAG_INT_ARRAY extends TAG {
	
	public int[]	Int;
	
	public TAG_INT_ARRAY() {
		super("");
	}
	
	public TAG_INT_ARRAY(String name) {
		super(name);
	}
	
	public TAG_INT_ARRAY(String name, int[] Int) {
		super(name);
		this.Int = Int;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		int length = stream.readInt();
		Int = new int[length];
		for (int i = 0; i < length; i++)
			Int[i] = stream.readInt();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeInt(Int.length);
		for (int i : Int)
			stream.write(i);
	}
	
	@Override
	public Integer[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public byte getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
