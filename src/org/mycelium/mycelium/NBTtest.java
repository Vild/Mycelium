package org.mycelium.mycelium;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.mycelium.mycelium.io.nbt.NBT;
import org.mycelium.mycelium.io.nbt.TAG;
import org.mycelium.mycelium.io.nbt.TAG_COMPOUND;

public class NBTtest {
	
	public static void main(String[] args) {
		//a("test.nbt", true);
		//a("test2.nbt", false);
		a("level.dat", true);
	}
	
	public static void a(String b, boolean c) {
		try {
			NBT nbt = new NBT(b, c);
			TAG_COMPOUND tags = nbt.GetTags();
			nbt.Close();
			w(tags);			
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
	}
	
	public static void w(TAG_COMPOUND tags) {
		for(TAG tag : tags.List) {
			switch(tag.getID()) {
				case 0x00: System.out.print("TAG_END"); break;
				case 0x01: System.out.print("TAG_BYTE"); break;
				case 0x02: System.out.print("TAG_SHORT");  break;
				case 0x03: System.out.print("TAG_INT"); break;
				case 0x04: System.out.print("TAG_LONG"); break;
				case 0x05: System.out.print("TAG_FLOAT"); break;
				case 0x06: System.out.print("TAG_DOUBLE"); break;
				case 0x07: System.out.print("TAG_BYTE_ARRAY"); break;
				case 0x08: System.out.print("TAG_STRING"); break;
				case 0x09: System.out.print("TAG_LIST"); break;
				case 0x0A: System.out.println("TAG_COMPOUND"); break;
				case 0x0B: System.out.print("TAG_INT_ARRAY"); break;
				default: System.out.print("TAG_???"); break;
			}
			if (tag.getID() == 0x0A)
				w((TAG_COMPOUND)tag);
			else
				System.out.println(" Name: " + tag.getName() + " Data: " + tag.getData());
		}
	}
	
}
