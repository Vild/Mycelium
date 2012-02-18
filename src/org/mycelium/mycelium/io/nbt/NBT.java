package org.mycelium.mycelium.io.nbt;

import java.util.HashMap;

public class NBT {
	
	HashMap<Byte, Class<?>> tags;
	
	public NBT(String path) {
		tags = new HashMap<Byte, Class<?>>();
		tags.put((byte) 0x0, TAG_END.class);
		
	}
	
	
	
}
