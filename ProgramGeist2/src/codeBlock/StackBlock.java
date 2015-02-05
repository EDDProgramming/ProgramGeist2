package codeBlock;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class StackBlock extends CodeBlock {
	
	public StackBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public StackBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	@Override
	public boolean call(int deltaMS) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return false;
	}
}
