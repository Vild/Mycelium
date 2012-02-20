package org.mycelium.mycelium.world.blocks;

import java.util.ArrayList;

import org.mycelium.mycelium.Vec3D;
import org.mycelium.mycelium.world.Item;

public abstract class Block implements Item {
	
	byte	data;
	Vec3D pos;
	
	public abstract int getID();

	public Vec3D getPos() {
		return pos;
	}
	
	public void setPos(Vec3D pos) {
		this.pos = pos;
	}
	
	public int getData() {
		return data;
	}
	
	public void setData(byte data) {
		this.data = data;
	}
	
	public void setData(byte data, boolean physics) {
		this.data = data;
		doPhysics();
	}
	
	public abstract boolean onPlace();
	
	public abstract boolean onBreak();
	
	public abstract void onBreaking();
	
	public abstract void onLeftclick();
	
	public abstract void onRigthclick();
	
	public abstract void doPhysics();
	
	// public abstract void remove();
	//
	// public Block getBlock(BlockSide blockside) {
	//
	// }
	
	public abstract ArrayList<Item> getDrops();
	
}
