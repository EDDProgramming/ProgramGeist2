package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import world.EntityWorld;

public class ConveyerBelt extends Entity {
	
	public ConveyerBelt(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		hitbox = makeRectangle(x + 100, y, 200, 10);
		image = new Image("res/Conveyer.png");
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return false;
	}

}
