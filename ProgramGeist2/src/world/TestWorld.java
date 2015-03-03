package world;

import main.Camera;

import org.newdawn.slick.SlickException;

import tile.*;
import codeBlock.StackBlock;
import entity.Ball;

public class TestWorld extends EntityWorld {
	public TestWorld(Camera c) throws SlickException {
		super(c);
		
		addEntity(new Ball(300f, 50f, this, 100.0f));
		
		for(int i = 0; i<10; i++) {
		addEntity(new Tile(100*i, 400, this));
		
		}
		
		for(int i = 0; i<10; i++) {
			addEntity(new Tile(100*i, -100, this));
			
		}
		
		for(int i = 0; i<5; i++) {
			addEntity(new Tile(700, 400 - i*100, this));
			
		}
		
		for(int i = 0; i<5; i++) {
			addEntity(new Tile(100, 400 - i*100, this));
			
		}
		
		addEntity(new TriangleTile(600, 300, this));

		
		//addEntity(new StackBlock(300, 200, this));
	}

}
