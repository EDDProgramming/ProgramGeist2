package codeBlock;

import ProgramGeist.world.EntityWorld;

public class HatBlock extends CodeBlock {
	
	public HatBlock(double x, double y, EntityWorld world) {
		super(x, y, world);
	}
	public HatBlock(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
	
	public void onTrigger(int deltaMS) {
		downBlock.update(deltaMS);
	}
	
}
