package codeBlock;

import world.EntityWorld;

public class StackBlock extends CodeBlock {
	
	public StackBlock(float x, float y, EntityWorld world) {
		super(x, y, world);
	}
	public StackBlock(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
}
