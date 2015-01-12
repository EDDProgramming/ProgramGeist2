package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	
	
	public Ball(double x, double y, EntityWorld world) throws SlickException {
		super(x, y, world);
		image = new Image("res/Ball.png");
	}

	@Override
	public boolean update(int deltaMS) {
		super.update(deltaMS);
		// TODO Auto-generated method stub
		return false;
	}
	
}
