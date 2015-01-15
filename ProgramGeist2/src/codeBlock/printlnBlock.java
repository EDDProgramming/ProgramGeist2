package codeBlock;

import world.EntityWorld;


public class printlnBlock extends StackBlock {
	
	String printText    = "";
	
	public printlnBlock(float x, float y, EntityWorld world) {
		super(x, y, world);
	}
	public printlnBlock(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
	
	public boolean update(int deltaMS) {
		System.out.println(printText);
		return true;
	}
}
