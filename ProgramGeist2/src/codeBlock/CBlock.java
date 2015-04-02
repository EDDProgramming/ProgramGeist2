package codeBlock;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.*;

public abstract class CBlock extends CodeBlock {
	
	protected ArrayList<CodeBlock> stack = new ArrayList<CodeBlock>();
	
	public CBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	
	public boolean call(int deltaMS) {
		return true;
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return false;
	}
}
