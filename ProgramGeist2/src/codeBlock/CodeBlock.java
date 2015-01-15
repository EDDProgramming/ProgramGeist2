package codeBlock;

import world.*;
import entity.*;

public class CodeBlock extends Entity{
	
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
	public CodeBlock(EntityWorld world) {
		this(0, 0, world);
	}
	public CodeBlock(float x, float y, EntityWorld world) {
		super(x, y, world);
	}
	public CodeBlock(CodeBlock downBlock, EntityWorld world) {
		this(downBlock.getX(), downBlock.getY()-20, world);
		this.downBlock = downBlock;
	}
	
	public boolean update(int deltaMS) {
		
		return true;
	}
	
}
