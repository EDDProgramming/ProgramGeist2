package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;

public abstract class HatBlock extends CodeBlock {
	
	public HatBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public HatBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	public void onTrigger(int deltaMS) {
		downBlock.update(deltaMS);
	}
	
	public boolean call(int deltaMS) {
		
		return true;
	}
	
}
