package codeBlock;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import world.*;

public class CBlock extends CodeBlock {
	
	protected ArrayList<CodeBlock> stack = new ArrayList<CodeBlock>();
	
	public CBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public CBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	
}
