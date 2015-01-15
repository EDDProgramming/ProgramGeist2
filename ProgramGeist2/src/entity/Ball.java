package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	
	
	public Ball(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, world, mass);
		image = new Image("res/Ball.png");
	}

	@Override
	public boolean update(int deltaMS) {
		super.update(deltaMS);
		
		return !removed;
	}
	
	public boolean update(int deltaMS, GameContainer gc) {
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_UP) == true)
		{
			applyForce(0, 5);
		}
		
		applyFriction(0.1);
		
		return false;
	}
	
	
}
