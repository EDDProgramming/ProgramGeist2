package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	boolean physicsEnabled = true;
	static Shape hitbox = new Circle(0, 0, 30);
	
	public Ball(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, hitbox, world, mass);
		image = new Image("res/Ball.png");
		entityType = EntityType.Ball;
	}

	public void movement(Input input) {
		//Apply forces using the <x, y> method
		
		float mag = 100;
		
		if(input.isKeyDown(Input.KEY_UP)) {
			applyForce(0, -mag - 50);
		}
				
		if(input.isKeyDown(Input.KEY_DOWN)) {
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
		
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			if(physicsEnabled == true) {
				physicsEnabled = false;
			}
			
			else {
				physicsEnabled = true;
			}
		}
		
		if(physicsEnabled == true) {
		
			System.out.println();
			System.out.println("Velocity: "+this.velocity);
			System.out.println("Acceleration: "+this.acceleration);
			System.out.println("Position: "+this.position);
		
			updateInit();
		
			movement(input);
		
			updateForces(0.5f);
		
			updatePosition();
		}
		
		return !removed;
	}
	
	protected void onCollide(Entity other) {
    	if(other.entityType == EntityType.Tile) {
    		
    		Line[] outline = getOutline(other.hitbox);
    		Line[] collided = new Line[outline.length];
    		
    		int collisions = 0;
    		
    		System.out.println("Collide");
    		
    		for(int i = 0; i < outline.length; i++) {
    			if(hitbox.intersects(outline[i])) {
    				System.out.println("Collided");
    				System.out.println(outline[i]);
    				collided[collisions] = outline[i];
    				collisions++;
    			}
    		}
    		
    		for(int i = 0; i < collisions; i++) {
    			
    			//Absolute value allows us to use the square value while maintaining the direction
        		float forceScalar = .008f * mass;
        		
    			Vector2f surface = lineToVector(collided[i]);
    			
    			Vector2f normal = surface.getPerpendicular();
    			
    			Vector2f tileCenter = new Vector2f(other.hitbox.getCenter());
    			
    			Vector2f normalDir = new Vector2f();
    			normalDir.x = position.x - tileCenter.x;
    			normalDir.y = position.y - tileCenter.y;
    			
    			//Make sure the normal is pointing outwards
    			if((normalDir.x > 0 && normal.x < 0) || (normalDir.x < 0 && normal.x > 0)) {
    				normal.x *= -1;
    			}
    			
    			if((normalDir.y > 0 && normal.y < 0) || (normalDir.y < 0 && normal.y > 0)) {
    				normal.y *= -1;
    			}
    			
    			normal.normalise();
    			
    			System.out.println(normal);
    		    		
    			Vector2f bounceDir = getReflectionVector(velocity, normal);
    		
    			Vector2f forceNormal = bounceDir.scale(forceScalar);
    			
    			System.out.println(forceNormal);
    			
    			//Bump the ball out
    			
    			if((normalDir.x > 0 && normal.x < 0) || (normalDir.x < 0 && normal.x > 0)) {
    				normal.x *= -1;
    			}
    			
    			if((normalDir.y > 0 && normal.y < 0) || (normalDir.y < 0 && normal.y > 0)) {
    				normal.y *= -1;
    			}
    			
    			normal.normalise();
    			Vector2f positionReset = new Vector2f();
    			System.out.println(normal);
    			positionReset =  normal.scale(hitbox.getBoundingCircleRadius() - getPerpendicularDistance(collided[i], hitbox.getCenterX(), hitbox.getCenterY()));
    			System.out.println(positionReset);
    			
    			position.x += positionReset.x;
    			position.y += positionReset.y;

        		//Impact force
        		
        		applyForce(forceNormal.x, forceNormal.y); 
    		}
    		
    	}
    	
    	if(other.entityType == EntityType.Ball) {
    		//Bumps the ball away
    		Vector2f positionReset = new Vector2f();
    		positionReset.x = position.x - other.getX();
    		positionReset.y = position.y - other.getY();
    		
    		float scalar = (hitbox.getBoundingCircleRadius() + other.hitbox.getBoundingCircleRadius()) - positionReset.length();
    		scalar /= 2;
    		positionReset.normalise();
    		positionReset.scale(scalar);
    		
    		//Adds a new bounce force
    		Vector2f force = new Vector2f();
    		force.x = position.x - other.getX();
    		force.y = position.y - other.getY();
    	}
    	
    }
	
	
}
