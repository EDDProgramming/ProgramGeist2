package codeBlock;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entity.PhysicsObject;
import world.EntityWorld;

public abstract class HatBlock extends CodeBlock {
	
	PhysicsObject object;
	
	public HatBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		loadImage("res/Code Blocks/The_shape_of_a_Hat_Block.png");
		canConnectUp = false;
		blockType = blockType.Hat;
	}
	
	public boolean call(int deltaMS) {
		if(downBlock != null) {
			downBlock.call(deltaMS);
		}
		return true;
	}
	
	public boolean update(int deltaMS) {
		return super.update(deltaMS);
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return super.update(deltaMS, input);
	}
	
	@Override
	public boolean update(int deltaMS, Input input, List<CodeBlock> blocks) {
		return super.update(deltaMS, input, blocks);
	}
	
}
