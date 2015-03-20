package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import world.EntityWorld;

public class Ball extends PhysicsObject {
	
	static Polygon hitbox = makeRectangle(0, 0, 15, 15);
	boolean physicsEnabled = true;
	
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
    			if(radius.intersects(outline[i])) {
    				System.out.println("Collided");
    				collided[collisions] = outline[i];
    				collisions++;
    			}
    		}
    		
    		for(int i = 0; i < collisions; i++) {
    			
    			//Absolute value allows us to use the square value while maintaining the direction
        		float forceScalar = .009f * mass;
    		
    			Vector2f surface = lineToVector(collided[i]);
    			
    			Vector2f normal = surface.getNormal();
    			
    			System.out.println(normal);
    		    		
    			Vector2f bounceDir = getReflectionVector(velocity, normal);
    		
    			Vector2f forceNormal = bounceDir.scale(forceScalar);
    			
    			//Bump the ball out
    			
    			position.y = prevPosition.y + velocity.y;
        		position.x = prevPosition.x + velocity.x;

        		//Impact force
        		
        		applyForce(forceNormal.x, forceNormal.y); 
    		}
    		
    	}
    	
    }
	
	
}
