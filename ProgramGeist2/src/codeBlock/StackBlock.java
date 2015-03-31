package codeBlock;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public abstract class StackBlock extends CodeBlock {
	
	public StackBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	
	@Override
	public boolean call(int deltaMS) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		super.update(deltaMS, input);
		// TODO Auto-generated method stub
		return false;
	}
}
