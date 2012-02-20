package org.mycelium.mycelium.io.world.anvil;

import org.mycelium.mycelium.Vec3D;
import org.mycelium.mycelium.world.World;
import org.mycelium.mycelium.world.blocks.Block;

public class WorldAnvil extends World {

	Block[][][] blocks = new Block[16][256][16];
	
	@Override
	public Block getBlock(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlock(Vec3D pos, Block block) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBlock(int x, int y, int z, Block block) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlock(Vec3D pos, Block block) {
		// TODO Auto-generated method stub
		
	}
	
}
