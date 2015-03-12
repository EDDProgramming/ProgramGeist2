package codeBlock;

import org.newdawn.slick.SlickException;

import world.EntityWorld;


public class printlnBlock extends StackBlock implements Cloneable {
	
	String printText    = "";
	
	public printlnBlock(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		canConnectUp = true;
		canConnectDown = true;
	}
	
	@Override
	public boolean update(int deltaMS) {
		call(deltaMS);
		return true;
	}
	
	public boolean call(int deltaMS) {
		System.out.println(printText);
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
