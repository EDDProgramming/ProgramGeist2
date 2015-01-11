package codeBlock;

import world.*;
import entity.*;

public class CodeBlock extends Entity{
	
	public enum blockType {
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
		this(0, 0, world);
		
		
	}
	public CodeBlock(double x, double y, EntityWorld world) {
		super(x, y, world);
	}
	
	public boolean update(int deltaMS) {
		
		
		return true;
	}
	
}
