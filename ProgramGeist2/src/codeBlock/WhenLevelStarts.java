package codeBlock;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class WhenLevelStarts extends HatBlock {
	
	public WhenLevelStarts(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		
		// TODO Integrate with Levels
		call(deltaMS);
		
		return true;
	}
	
	
	public CodeBlock clone() {
		try {
			return new WhenLevelStarts(position.x, position.y, world);
		} catch (SlickException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
