package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;


public class printlnBlock extends StackBlock {
	
	String printText    = "";
	
	public printlnBlock(double x, double y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public printlnBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	public boolean update(int deltaMS) {
		System.out.println(printText);
		return true;
	}
}
