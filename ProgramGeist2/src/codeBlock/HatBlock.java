package codeBlock;

import world.EntityWorld;

public class HatBlock extends CodeBlock {
	
	public HatBlock(float x, float y, EntityWorld world) {
		super(x, y, world);
	}
	public HatBlock(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
	
	public void onTrigger(int deltaMS) {
		downBlock.update(deltaMS);
	}
	
}
