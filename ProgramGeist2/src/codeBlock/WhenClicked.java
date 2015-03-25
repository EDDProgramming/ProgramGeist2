package codeBlock;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class WhenClicked extends HatBlock {
	public WhenClicked(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
	}
	public WhenClicked(CodeBlock downBlock, EntityWorld world) throws SlickException {
		super(downBlock, world);
	}
	
	@Override
	public boolean update(int deltaMS) {
		// TODO Integrate with Levels
		
		
		
		return true;
	}
	
	@Override
	public boolean update(int deltaMS, Input input, List<CodeBlock> blocks) {
		
		
		
		return super.update(deltaMS, input, blocks);
	}
	
	public boolean call(int deltaMS) {
		downBlock.call(deltaMS);
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
