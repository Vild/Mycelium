package org.mycelium.mycelium.world.blocks;

import java.util.ArrayList;

import org.mycelium.mycelium.io.Log;
import org.mycelium.mycelium.world.Item;

public class BlockAir extends Block {
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public boolean onPlace() {
		return true;
	}
	
	@Override
	public boolean onBreak() {
		return true;
	}
	
	@Override
	public void onBreaking() {
	}
	
	@Override
	public void onLeftclick() {
	}
	
	@Override
	public void onRigthclick() {
	}
	
	@Override
	public void doPhysics() {
	}
	
	@Override
	public ArrayList<Item> getDrops() {
		ArrayList<Item> drops = new ArrayList<Item>();
		//drops.add(this);
		return drops;
	}
	
}
