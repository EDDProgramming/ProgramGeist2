package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	static Polygon hitbox = makeRectangle(0, 0, 15, 15);
	
	public Ball(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, hitbox, new Circle(x, y, 30), true, world, mass);
		image = new Image("res/Ball.png");
	}

	@Override
	public boolean update(int deltaMS) {
		super.update(deltaMS);
		
		System.out.println();
		System.out.println("Velocity: "+this.velocity.y);
		System.out.println("Acceleration: "+this.acceleration.y);
		System.out.println("Position: "+radius.getCenterY());
		
		return !removed;
	}
	
	public boolean update(int deltaMS, GameContainer gc) {
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_UP) == true)
		{
			applyForce(0, -500);
		}
		
		applyFriction(0.1f);
		
		return false;
	}
	
	
	public double getCollisionRadius() {
		return 60.0/2;
	}
	
	
}
