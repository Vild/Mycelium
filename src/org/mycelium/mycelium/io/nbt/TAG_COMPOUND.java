package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TAG_COMPOUND extends TAG {
	
	public ArrayList<TAG>	List;
	
	public TAG_COMPOUND() {
		super("");
	}
	
	public TAG_COMPOUND(String name) {
		super(name);
	}
	
	public TAG_COMPOUND(String name, ArrayList<TAG> List) {
		super(name);
		this.List = List;
	}
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.List.clear();
		byte id;
		while ((id = stream.readByte()) != 0) {
			TAG tag = TAG.createTag(id, stream.readUTF());
			tag.Read(stream);
			this.List.add(tag);
		}
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		for (TAG tag : this.List) {
			stream.writeByte(tag.GetId());
			stream.writeUTF(tag.name);
			tag.Write(stream);
		}
		stream.writeByte(0);
	}
	
	@Override
	public byte GetId() {
		return 0x10;
	}
	
}
