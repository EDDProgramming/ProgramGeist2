package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import entity.Entity.EntityType;
import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	static Polygon hitbox = makeRectangle(0, 0, 15, 15);
	
	public Ball(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, hitbox, new Circle(x, y, 33), true, world, mass);
		image = new Image("res/Ball.png");
	}

	public void movement(Input input) {
		//Apply forces using the <x, y> method
		
		float mag = 100;
		
		if(input.isKeyDown(Input.KEY_UP)) {
			applyForce(0, -mag - 50);
		}
				
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			applyForce(0, mag);
		}
				
		if(input.isKeyDown(Input.KEY_LEFT)) {
			applyForce(-mag, 0);
		}
				
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			applyForce(mag, 0);
		}
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		super.update(deltaMS);
		
		
		System.out.println();
		System.out.println("Velocity: "+this.velocity.y);
		System.out.println("Acceleration: "+this.acceleration.y);
		System.out.println("Position: "+radius.getCenterY());
		System.out.println("RealPos: "+this.position.y);
		
		updateInit();
		
		movement(input);
		
		updateForces(0.5f);
		
		updatePosition();
		
		return !removed;
	}
	
	protected void onCollide(Entity other) {
    	if(other.entityType == EntityType.Tile) {
    		
    		//Absolute value allows us to use the square value while maintaining the direction
    		float forceScalar = .045f * mass;
    		
    		Line[] outline = getOutline(other.hitbox);
    		Line collided = new Line(0, 0);
    		
    		for(int i = 0; i < outline.length; i++) {
    			if(radius.intersects(outline[i])) {
    				System.out.println("Collided");
    				collided = outline[i];
    				return;
    			}
    		}
    		
    		Vector2f normal = lineToVector(collided).getNormal();
    		
    		Vector2f bounceDir = getReflectionVector(velocity, normal);
    		
    		Vector2f forceNormal = bounceDir.scale(forceScalar);
    		
    		System.out.println("Collide");
    		
    		//Bump the ball back up
    		position.y = prevPosition.y - velocity.y;
    		applyForce(forceNormal.x, forceNormal.y); // impact force
    	}
    	
    }
	
	
}
