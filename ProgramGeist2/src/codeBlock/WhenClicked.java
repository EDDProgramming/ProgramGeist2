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
	
	@Override
	public boolean update(int deltaMS) {
		//System.out.println("This is the Wrong Update - When Clicked Block");
		return super.update(deltaMS);
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
