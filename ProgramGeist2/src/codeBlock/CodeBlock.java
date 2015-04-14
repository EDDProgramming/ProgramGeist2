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

public abstract class CodeBlock extends Entity {
	
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
	
	protected Boolean connectedUp   = false;
	protected Boolean fullyConnected = false; // maybe we don't need this
	
	protected boolean menuMode = false;
	protected static int mouseID = -1;
	protected boolean mouseDown = false;
	
	protected float mouseOnX;

	protected float mouseOnY;
	
	// Constructors
	public CodeBlock(EntityWorld world) throws SlickException {
		this(0, 0, world);
	}

	public CodeBlock(float x, float y, EntityWorld world) {
		super(x, y, world);
		
		hitbox = makeRectangle(x, y, 100, 20);
		loadImage("res/Code Blocks/The_shape_of_a_Stack_Block.png");
	}
	
	/*
	 * loadImage
	 * 
	 * loads an image to represent this code block.
	 * 
	 */
	protected void loadImage(String filepath) {
		try {
			loadImage(new Image(filepath));
		}catch (SlickException e) {
			System.out.println("Could not find image for code block");
			e.printStackTrace();
		}
	}
	protected void loadImage(Image I) {
		image = I;
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		
		
		return true;
	}

	@Override
	public boolean update(int deltaMS, Input input) {
		this.update(deltaMS);
		centerHitbox();
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
	
	public void centerHitbox() {
		hitbox.setX(position.x);
		hitbox.setY(position.y);
	}
	
	/*
	 * checkConnections
	 * 
	 * this method will check:
	 * 1 - if the block is in a position to make a connection
	 * 2 - if the block has a connection it will move the block to stick on its upBlock.
	 * 
	 */
	public void checkConnections(Input input, List<CodeBlock> blocks) {
		if((canConnectUp || canConnectDown) && !getMenu()) {
			Iterator<CodeBlock> c = blocks.iterator();
			while(c.hasNext()) {
				CodeBlock block = c.next();
				
				if(block != this && !block.getMenu()) {
					// TODO Fix code block connections
					
					// check for up block
					if(canConnectUp) {
						if(block != downBlock) {
							if(position.x >= block.getX()-100
									&& position.x <= block.getX()+100
									&& position.y >= block.getY()
									&& position.y <= block.getY()+24) {
								setUpBlock(block);
								block.setDownBlock(this);
							}
						}
					}
					// check for down block
					if(canConnectDown) {
						if(block != upBlock && block.blockType != blockType.Hat) {
							if(position.x >= block.getX()-100
									&& position.x <= block.getX()+100
									&& position.y >= block.getY()-24
									&& position.y <= block.getY()) {

								setDownBlock(block);
								block.setUpBlock(this);
								canConnectDown = false;
							}
						}
					}
				}
			}
		}
		if(connectedUp) {
			position.x = upBlock.getX();
			position.y = upBlock.getY()+20;
		}
	}
	
	/*
	 * mouseUpdate
	 * 
	 * handles interactions between code blocks and the mouse.
	 * 
	 */
	public void mouseUpdate(int deltaMS, Input input) {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		//Testing new input system
		
		if(input.isMousePressed(0) && hitbox.contains(mouseX, mouseY)) {
			if(menuMode) {
				try {
					CodeBlock c = this.clone();
					world.addEntity(c);
					c.mouseOnX = mouseX-c.position.x;
					c.mouseOnY = mouseY-c.position.y;
					mouseID = c.id;
				} catch (Exception e) {
					System.out.println("Menu Clone Exception");
					e.printStackTrace();
				}
			}
			else {	
				mouseID = id;
				mouseOnX = mouseX-position.x;
				mouseOnY = mouseY-position.y;
			}
		}
		
		if(!input.isMouseButtonDown(0)) {
			mouseID = -1;
		}
		
		if(mouseID == this.id) {
			position.x = mouseX-mouseOnX;
			position.y = mouseY-mouseOnY;
		}
		
		
//		if(input.isMouseButtonDown(0) && !mouseDown) {
//			if(hitbox.contains(mouseX, mouseY)) {
//			//if(mouseX >= position.x && mouseX <= position.x+100 && mouseY >= position.y && mouseY <= position.y+24) {
//				mouseDown = true;
//				if(menuMode) {
//					try {
//						CodeBlock c = this.clone();
//						world.addEntity(c);
//						mouseOnX = mouseX-c.position.x;
//						mouseOnY = mouseY-c.position.y;
//						mouseID = c.id;
//					} catch (Exception e) {
//						System.out.println("Menu Clone Exception");
//						e.printStackTrace();
//					}
//				} else {
//					mouseOnX = mouseX-position.x;
//					mouseOnY = mouseY-position.y;
//					mouseID = this.id;
//				}
//			}
//		}
//		
//		if(input.isMouseButtonDown(0)){
//			if(mouseID == this.id) {
//				if(connectedUp) {
//					if(mouseX > position.x+mouseOnX+50
//							|| mouseX < position.x+mouseOnX-50
//							|| mouseY > position.y+mouseOnY+50
//							|| mouseY < position.y+mouseOnY-50) {
//						disconnectUp();
//					}
//				} else {
//					position.x = mouseX-mouseOnX;
//					position.y = mouseY-mouseOnY;
//				}
//			}
//		}else {
//			mouseDown = false;
//			mouseID = -1;
//		}
	}
	
	@Override
	public abstract CodeBlock clone();
	
	@Override
	public void render(Graphics g, double camX, double camY) {
		g.drawImage(image, (float)(position.x-camX), (float)(position.y-camY));
	}
	
	public abstract boolean call(int deltaMS);
	
	public boolean getMenu() {
		return menuMode;
	}
	
	public void setMenu() {
		menuMode = true;
	}
	
	public void setDownBlock(CodeBlock newDownBlock) {
		downBlock = newDownBlock;
		canConnectDown = false;
	}
	
	public void setX(float newX) {
		position.x = newX;
	}
	
	public void setUpBlock(CodeBlock newUpBlock) {
		upBlock = newUpBlock;
		canConnectUp = false;
		connectedUp = true;
	}
	
	public void disconnectUp() {
		upBlock = null;
		canConnectUp = true;
		connectedUp = false;
		position.y+=100;
	}
}
