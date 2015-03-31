package world;

import main.Camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import tile.*;
import codeBlock.StackBlock;
import entity.Ball;
import gui.CatalogMenu;

public class TestWorld2 extends EntityWorld {
	
	private CatalogMenu catalogMenu;

	public TestWorld2(Camera c) throws SlickException {
		super(c);
		
		addEntity(new Ball(300f, 50f, this, 100.0f));
		
		for(int i = 0; i<10; i++) {
		addEntity(new Tile(100*i, 500, this));
		
		}
		
		for(int i = 0; i<10; i++) {
			addEntity(new Tile(100*i, -100, this));
			
		}
		
		for(int i = 0; i<5; i++) {
			addEntity(new Tile(800, 400 - i*100, this));
			
		}
		
		for(int i = 0; i<5; i++) {
			addEntity(new Tile(100, 400 - i*100, this));
			
		}
		
		for(int i = 0; i < 5; i ++) {
			addEntity(new TriangleTile2(200 + 100 * i, 100 * i, this));
		}
		
		for(int i = 0; i < 5; i ++) {
			addEntity(new Tile(200 + 100 * i, 100 + 100 * i, this));
		}
		
		CatalogMenu cm = new CatalogMenu(this);
		
		catalogMenu = cm;
	}
	
	public void update(GameContainer gc, int deltaMS) {
    	super.update(gc, deltaMS);
    	
    	Input input = gc.getInput();
    	
    	if(catalogMenu.isVisible()) {
    		catalogMenu.update(gc, deltaMS);
    	}
    	
    	if(input.isKeyPressed(Input.KEY_E)) {
    		catalogMenu.setVisible(!catalogMenu.isVisible());
    	}
    }
    
    public void render(GameContainer gc, Graphics g, double camX, double camY) {
    	super.render(gc, g, camX, camY);
        catalogMenu.render(gc, g);
    }

}
