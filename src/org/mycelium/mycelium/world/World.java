package org.mycelium.mycelium.world;

import org.mycelium.mycelium.Vec3D;
import org.mycelium.mycelium.world.blocks.Block;

public abstract class World {
	public static final int ANVIL_VERSION_ID = 0x4ABD;
	
	public abstract Block getBlock(int x, int y, int z);
	
	public abstract Block getBlock(Vec3D pos, Block block);
	
	public abstract void setBlock(int x, int y, int z, Block block);
	
	public abstract void setBlock(Vec3D pos, Block block);
	
}
