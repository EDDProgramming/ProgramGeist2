package codeBlock;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import world.*;
import entity.*;

public abstract class CodeBlock extends Entity implements Cloneable{
	
	public enum BlockType {
		Generic,
		Hat,
		Stack,
		Boolean,
		Reporter,
		C,
		Cap
	}
	
	protected BlockType blockType = BlockType.Generic;
	
	protected CodeBlock downBlock = null;
	protected CodeBlock upBlock   = null;
	protected static Circle radius = new Circle(0, 0, 10);
	
	protected boolean menuMode = false;
	protected boolean onMouse = false;
	
	// Constructors
	public CodeBlock(EntityWorld world) throws SlickException {
		this(0, 0, world);
	}
	public CodeBlock(float x, float y, EntityWorld world) {
		this(x, y, makeRectangle(x, y, 50, 20), radius, world);
		//Make the makeRectangle the correct size
	}
	public CodeBlock(float x, float y, EntityWorld world, boolean menu) {
		this(x, y, makeRectangle(x, y, 50, 20), radius, world);
		menuMode = menu;
	}
	public CodeBlock(float x, float y,Polygon hitbox, Circle radius, EntityWorld world) {
		super(x, y, hitbox, radius, false, world);
		entityType = Entity.EntityType.CodeBlock;
		
		try {
			image = new Image("res/Code Blocks/The_shape_of_a_Stack_Block.png");
		} catch(SlickException e) {
			e.printStackTrace();
			System.out.println("IMAGE NOT FOUND");
		}
	}
	public CodeBlock(CodeBlock downBlock, EntityWorld world) throws SlickException {
		this(downBlock.getX(), downBlock.getY()-20,  world);
		this.downBlock = downBlock;
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		return true;
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if(input.isMouseButtonDown(0)) {
			if(onMouse) {
				// TODO make the code block stick where the mouse originally clicked on it.
				position.x = mouseX-50;
				position.y = mouseY-20;
			}else if(mouseX >= position.x && mouseX <= position.x+100 && mouseY >= position.y && mouseY <= position.y+40) {
				if(menuMode) {
					CodeBlock spawn = (CodeBlock) this.clone();
					
				}else {
					onMouse = true;
				}
			}else {
				onMouse = false;
			}
		}
			return true;
	}
	
	@Override
	public CodeBlock clone() {
		
	}
	
	@Override
	public void render(Graphics g, double camX, double camY) {
		g.drawImage(image, (float)(position.x-camX), (float)(position.y-camY));
	}
	
	public abstract boolean call(int deltaMS);
	
	public void setMenu() {
		menuMode = true;
	}
	
}
