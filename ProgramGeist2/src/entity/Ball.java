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
		System.out.println("Velocity: "+this.velocity);
		System.out.println("Acceleration: "+this.acceleration);
		System.out.println("Position: "+this.position);
		
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
    		Line collided = new Line(1, 1);
    		
    		System.out.println("Collide");
    		
    		for(int i = 0; i < outline.length; i++) {
    			System.out.println("For Loop");
    			if(radius.intersects(outline[i])) {
    				System.out.println("Collided");
    				collided = outline[i];
    				break;
    			}
    		}
    		
    		Vector2f surface = lineToVector(collided);
    		
    		Vector2f normal = surface.getNormal();
    		
    		Vector2f bounceDir = getReflectionVector(velocity, normal);
    		
    		Vector2f forceNormal = bounceDir.scale(forceScalar);
    		
    		System.out.println("Direction X: "+forceNormal.x);
    		System.out.println("Direction Y: "+forceNormal.y);
    		
    		//Bump the ball out
    		position.y = prevPosition.y - (velocity.y * 2);
    		
    		applyForce(forceNormal.x, forceNormal.y); // impact force
    	}
    	
    }
	
	
}
