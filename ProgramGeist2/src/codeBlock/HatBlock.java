package codeBlock;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public abstract class HatBlock extends CodeBlock {
	
	public HatBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public HatBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	public boolean call(int deltaMS) {
		
		return true;
	}
	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(int deltaMS, Input input, List<CodeBlock> blocks) {
		return super.update(deltaMS, input, blocks);
	}
	
}
