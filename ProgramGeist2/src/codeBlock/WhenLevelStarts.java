package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class WhenLevelStarts extends HatBlock {
	
	public WhenLevelStarts(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public WhenLevelStarts(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		// TODO Integrate with Levels
		downBlock.update(deltaMS);
		
		return true;
	}
	
}
