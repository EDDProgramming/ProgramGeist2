package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	
	
	public Ball(double x, double y, EntityWorld world, double d) throws SlickException {
		super(x, y, world, d);
		image = new Image("res/Ball.png");
	}

	@Override
	public boolean update(int deltaMS) {
		super.update(deltaMS);
		
		return !removed;
	}
	
	
}
