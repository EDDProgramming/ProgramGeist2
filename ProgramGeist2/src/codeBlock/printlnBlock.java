package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;


public class printlnBlock extends StackBlock implements Cloneable {
	
	String printText    = "PRINTLN BLOCK";
	
	public printlnBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		canConnectUp = true;
		canConnectDown = true;
	}
	
	public printlnBlock(float x, float y, EntityWorld world, String _printText) throws SlickException {
		this(x, y, world);
		printText = _printText;
	}
	
	@Override
	public boolean update(int deltaMS) {
		return true;
	}
	
	public boolean call(int deltaMS) {
		System.out.println(printText);
		if(downBlock != null) {
			downBlock.call(deltaMS);
		}
		return true;
	}
	@Override
	public CodeBlock clone() {
		try {
			System.out.println("CLONING PRINTLN BLOCK");
			return new printlnBlock(position.x, position.y, world);
		} catch (SlickException e) {
			System.out.println("ERROR CLONING PRINTLN BLOCK");
			e.printStackTrace();
			return null;
		}
	}
}
