package codeBlock;

import java.util.ArrayList;

import ProgramGeist.world.*;

public class CBlock extends CodeBlock {
	
	protected ArrayList<CodeBlock> stack = new ArrayList<CodeBlock>();
	
	public CBlock(double x, double y, EntityWorld world) {
		super(x, y, world);
	}
	public CBlock(CodeBlock downBlock, EntityWorld world) {
		super(downBlock, world);
	}
	
	
}
