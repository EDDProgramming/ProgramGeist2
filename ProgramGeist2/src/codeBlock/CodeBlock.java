package codeBlock;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import world.*;
import entity.*;

public abstract class CodeBlock extends Entity {
	
	public enum BlockType {
		Generic,
		Hat,
		Stack,
		Boolean,
		Reporter,
		C,
		Cap
	}
	
	protected BlockType blockType = BlockType.Generic;
	
	protected CodeBlock downBlock = null;
	protected CodeBlock upBlock   = null;
	
	
	// Constructors
	public CodeBlock(EntityWorld world) throws SlickException {
		this(0, 0, world);
	}
	public CodeBlock(float x, float y, EntityWorld world) {
		super(x, y, hitbox, radius, false, world);
	}
	public CodeBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		this(downBlock.getX(), downBlock.getY()-20, world);
		this.downBlock = downBlock;
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		
		return true;
	}
	
	public abstract boolean call(int deltaMS);
	
}
