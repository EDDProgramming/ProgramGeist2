package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class HatBlock extends CodeBlock {
	
	public HatBlock(double x, double y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public HatBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	public void onTrigger(int deltaMS) {
		downBlock.update(deltaMS);
	}
	
}
