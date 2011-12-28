package org.mycelium.mycelium.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketLogin extends Packet {
	
	public int		ProtocalVersion;
	public String	Username;
	
	public int		EntityId;			// of player
	public long		MapSeed;
	public int		ServerMode;		// 0 survival, 1 creative
	public byte		Dimension;			// -1 Nether, 0 Overworld, 1 The End
	public byte		Difficulty;		// 0 Peaceful, 1 Easy, 2 Normal, 3 Hard
	public int		WorldHeight;
	public int		MaxPlayers;
	
	@Override
	public void Read(DataInputStream stream) throws IOException {
		this.ProtocalVersion = stream.readInt();
		this.Username = ReadString(stream, 16);
		stream.readLong();
		stream.readInt();
		stream.readByte();
		stream.readByte();
		stream.readUnsignedByte();
		stream.readUnsignedByte();
	}
	
	@Override
	public void Write(DataOutputStream stream) throws IOException {
		stream.writeInt(this.EntityId);
		WriteString(stream, "");
		stream.writeLong(this.MapSeed);
		stream.writeInt(this.WorldHeight);
		stream.writeByte(this.Dimension);
		stream.writeByte(this.Difficulty);
		stream.writeInt(this.WorldHeight);
		stream.writeInt(this.MaxPlayers);
	}
	
	@Override
	public int getId() {
		return 0x01;
	}
	
}
