package codeBlock;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.SlickException;

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
	public CodeBlock(EntityWorld world) throws SlickException {
		this(0, 0, world);
	}
	public CodeBlock(double x, double y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public CodeBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		this(downBlock.getX(), downBlock.getY()-20, world);
		this.downBlock = downBlock;
	}
	
	public boolean update(int deltaMS) {
		
		
		return true;
	}
	
}
