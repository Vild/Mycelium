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
	public void Read(DataInputStream stream) throws IOException { //TODO: Add Read to TAG_INT_ARRAY
		//this.Int = new int[stream.readInt()];
		//stream.read(this.Int);
	}
		
	@Override
	public void Write(DataOutputStream stream) throws IOException { //TODO: Add Write to TAG_INT_ARRAY
		//stream.writeInt(this.Int.length);
		//stream.write(this.Int);
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
