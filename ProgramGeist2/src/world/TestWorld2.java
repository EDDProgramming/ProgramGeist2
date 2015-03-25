package world;

import main.Camera;

import org.newdawn.slick.SlickException;

import tile.*;
import codeBlock.StackBlock;
import entity.Ball;

public class TestWorld2 extends EntityWorld {

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
	}

}
