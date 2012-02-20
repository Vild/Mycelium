package org.mycelium.mycelium.world.blocks;

import java.util.ArrayList;

import org.mycelium.mycelium.io.Log;
import org.mycelium.mycelium.world.Item;

public class BlockStone extends Block {
	
	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public boolean onPlace() {
		Log.getLog().Info("Placed a stone block @ " + getPos());
		return true;
	}
	
	@Override
	public boolean onBreak() {
		Log.getLog().Info("Breaked a stone block @ " + getPos());
		return true;
	}
	
	@Override
	public void onBreaking() {
		Log.getLog().Info("Breaking a stone block @ " + getPos());
	}
	
	@Override
	public void onLeftclick() {
		Log.getLog().Info("Leftclick a stone block @ " + getPos());
	}
	
	@Override
	public void onRigthclick() {
		Log.getLog().Info("Rigthclick a stone block @ " + getPos());
	}
	
	@Override
	public void doPhysics() {
		Log.getLog().Info("Doing physics on a stone block @ " + getPos());
	}
	
	@Override
	public ArrayList<Item> getDrops() {
		ArrayList<Item> drops = new ArrayList<Item>();
		drops.add(this);
		return drops;
	}
	
}
