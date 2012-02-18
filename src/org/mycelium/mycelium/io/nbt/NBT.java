package org.mycelium.mycelium.io.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBT {
	
	File				file;
	DataInputStream		istream;
	DataOutputStream	ostream;
	
	public NBT() {
	}
	
	public NBT(String path) throws IOException {
		this.Load(path, true);
	}
	
	public NBT(String path, boolean gzip) throws IOException {
		this.Load(path, gzip);
	}
	
	public void Load(String path, boolean gzip) throws IOException {
		file = new File(path);
		istream = gzip ? new DataInputStream(new GZIPInputStream(new FileInputStream(file))) : new DataInputStream(new FileInputStream(file));
		ostream = gzip ? new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file))) : new DataOutputStream(new FileOutputStream(file));
		
	}
	
	public void Close() throws IOException {
		istream.close();
		ostream.close();
		file = null;
	}
	
	public TAG_COMPOUND GetTags() throws IOException, DataFormatException {
		if (istream.readByte() == 0x10) {
			TAG_COMPOUND tags = new TAG_COMPOUND(istream.readUTF());
			tags.Read(istream);
			return tags;
		} else {
			throw new DataFormatException("Must start with a TAG_COMPOUND");
		}
	}
	
	public void WriteTags(TAG_COMPOUND tags) throws IOException {
		ostream.writeByte(0x10);
		tags.Write(ostream);
	}
}
