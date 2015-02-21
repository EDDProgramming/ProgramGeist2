package codeBlock;

import java.util.Iterator;
import java.util.List;

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
	protected CodeBlock inBlock   = null;
	
	protected Boolean canConnectUp   = false;
	protected Boolean canConnectDown = false;
	
	protected Boolean connected   = false;
	protected Boolean fullyConnected = false; // maybe we don't need this
	
	protected static Circle radius = new Circle(0, 0, 10);
	
	protected boolean menuMode = false;
	protected static boolean onMouse = false;
	protected static int mouseID = -1;
	protected boolean mouseDown = false;
	
	protected float mouseOnX;
	protected float mouseOnY;
	
	// Constructors
	public CodeBlock(EntityWorld world) throws SlickException {
		this(0, 0, world);
	}
	public CodeBlock(float x, float y, EntityWorld world) {
		this(x, y, makeRectangle(x, y, 50, 20), radius, world);
		// TODO Make the makeRectangle the correct size
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
		
		mouseUpdate(deltaMS, input);
		
		return true;
	}
	
	public boolean update(int deltaMS, Input input, List<CodeBlock> blocks) {
		this.update(deltaMS, input);
		
		if(!menuMode) {
			checkConnections(input, blocks);
		}
		
		return true;
	}
	
	/*
	 * checkConnections
	 * 
	 * this method will check:
	 * 1- if the block is in a position to make a connection
	 * 
	 */
	public void checkConnections(Input input, List<CodeBlock> blocks) {
		Iterator<CodeBlock> c = blocks.iterator();
		
		if(canConnectUp || canConnectDown) {
			while(c.hasNext()) {
				CodeBlock block = c.next();

				if(block != this) {
					// TODO Fix code block connections

					// check for up block
					if(canConnectUp) {
						if(position.x >= block.getX()-100
								&& position.x <= block.getX()+100
								&& position.y >= block.getY()
								&& position.y <= block.getY()+50) {

							setUpBlock(block);
							block.setDownBlock(this);
							canConnectUp = false;
						}
					}
					if(canConnectDown) {
						// TODO check for down block
						if(position.x >= block.getX()-100
								&& position.x <= block.getX()+100
								&& position.y >= block.getY()-50
								&& position.y <= block.getY()) {

							setDownBlock(block);
							block.setUpBlock(this);
							canConnectDown = false;
						}
					}
				}
			}
		}
		
		if(upBlock != null) {
			position.x = upBlock.getX();
			position.y = upBlock.getY()+50;
		}
		
	}
	
	public void mouseUpdate(int deltaMS, Input input) {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if(input.isMouseButtonDown(0)) {
			if(onMouse) {
				if(mouseID == this.id) {
					position.x = mouseX-mouseOnX;
					position.y = mouseY-mouseOnY;
				}
			}else if(mouseX >= position.x && mouseX <= position.x+100 && mouseY >= position.y && mouseY <= position.y+40) {
				if(menuMode) {
						try {
							world.addEntity(clone());
						} catch (Exception e) {
							System.out.println("Menu Clone Exception");
							e.printStackTrace();
						}
				}else {
					mouseOnX = mouseX-position.x;
					mouseOnY = mouseY-position.y;
					onMouse = true;
					mouseID = this.id;
				}
			}
			mouseDown = true;
		}else {
			onMouse = false;
			mouseDown = false;
		}
	}
	
	@Override
	public abstract CodeBlock clone();
	
	@Override
	public void render(Graphics g, double camX, double camY) {
		g.drawImage(image, (float)(position.x-camX), (float)(position.y-camY));
	}
	
	public abstract boolean call(int deltaMS);
	
	public void setMenu() {
		menuMode = true;
	}
	
	public void setDownBlock(CodeBlock newDownBlock) {
		downBlock = newDownBlock;
	}
	
	public void setUpBlock(CodeBlock newUpBlock) {
		downBlock = newUpBlock;
	}
}
