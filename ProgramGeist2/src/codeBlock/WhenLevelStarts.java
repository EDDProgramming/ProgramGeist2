package codeBlock;

import ProgramGeist.world.EntityWorld;

public class WhenLevelStarts extends HatBlock {
	
	public WhenLevelStarts(double x, double y, EntityWorld world) {
		super(x, y, world);
	}
	public WhenLevelStarts(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
	
	public boolean update(int deltaMS) {
		
		// TODO Integrate with Levels
		downBlock.update(deltaMS);
		
		return true;
	}
	
}
