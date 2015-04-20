package codeBlock;

import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class WhenClicked extends HatBlock {
	public WhenClicked(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
	}
	
	@Override
	public boolean update(int deltaMS, Input input, List<CodeBlock> blocks) {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if(input.isMouseButtonDown(0) && !getMenu()) {
			if(hitbox.contains(mouseX, mouseY)) {
				call(deltaMS);
			}
		}
		
		return super.update(deltaMS, input, blocks);
	}
	
	public CodeBlock clone() {
		try {
			return new WhenClicked(position.x, position.y, world);
		} catch (SlickException e) {
			e.printStackTrace();
			return null;
		}
	}
}
