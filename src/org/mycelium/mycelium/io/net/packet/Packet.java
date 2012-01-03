package org.mycelium.mycelium.io.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
	
	public abstract byte getId();

	public abstract void Read(DataInputStream stream) throws IOException;
	
	public abstract void Write(DataOutputStream stream) throws IOException;
	
	public static String ReadString(DataInputStream stream, int maxlength) throws IOException {
		short length = stream.readShort();
		
		if (length > maxlength)
			throw new IOException("Recived String length longer then maximum allowed (" + length + " > " + maxlength + ")");
		else if (length < 0)
			throw new IOException("Recived String length is less then zero!");  //Weird string :S
		else {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < length; i++)
				sb.append(stream.readChar());
			
			return sb.toString();
		}
	}
	
	public static void WriteString(DataOutputStream stream, String text) throws IOException {
		if (text.length() > 32767)
			throw new IOException("String too big");
		else {
			stream.writeShort(text.length());
			stream.writeChars(text);
		}
	}
}
