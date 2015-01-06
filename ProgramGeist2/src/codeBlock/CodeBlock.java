package codeBlock;

import ProgramGeist.entity.*;
import ProgramGeist.world.*;

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
	
	// Constructors
	public CodeBlock(EntityWorld world) {
		super(world);
	}
	public CodeBlock(double x, double y, EntityWorld world) {
		super(x, y, world);
	}
	
	public boolean update(int deltaMS) {
		
		
		return true;
	}
	
}
