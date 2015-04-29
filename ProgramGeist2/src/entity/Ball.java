package entity;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import de.lessvoid.nifty.Nifty;
import world.EntityWorld;

public class Ball extends PhysicsObject {
	boolean physicsEnabled = true;

	public Ball(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, world, mass);
		hitbox = new Circle(0, 0, 26);
		currentImage = new Image("res/Ball.png");
		entityType = EntityType.Ball;
	}

	public void movement(Input input) {
		//Apply forces using the <x, y> method
		
		float mag = 40;
		
		if(input.isKeyDown(Input.KEY_UP)) {
			applyForce(0, -mag - 20);
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
	public boolean update(int deltaMS, Input input, Nifty nifty) {
		super.update(deltaMS, input, nifty);
		
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			if(physicsEnabled == true) {
				physicsEnabled = false;
			}
			
			else {
				physicsEnabled = true;
			}
		}
		
		if(physicsEnabled == true) {
		
			//System.out.println();
			//System.out.println("Velocity: "+this.velocity);
			//System.out.println("Acceleration: "+this.acceleration);
			//System.out.println("Position: "+this.position);
		
			updateInit();
		
			movement(input);
		
			updateForces(0.5f);
		
			updatePosition(deltaMS);
		}
		
		return !removed;
	}
	
	protected void onCollide(Entity other, List<Entity> entities) {
		
		//**------------Tile Collision-------------**//
		
    	if(other.entityType == EntityType.Tile) {
    		
    		Line[] outline = getOutline(other.hitbox);
    		Line[] collided = new Line[outline.length];
    		
    		int collisions = 0;
    		
    		//System.out.println("Collide");
    		
    		for(int i = 0; i < outline.length; i++) {
    			if(hitbox.intersects(outline[i])) {
    				//System.out.println("Collided");
    				collided[collisions] = outline[i];
    				collisions++;
    			}
    		}
    		
    		boolean[] ignore = new boolean[collisions];

    		
    		//determines what collisions to ignore
    		for(int i = 0; i < collisions; i++) {
    			float[] line1 = collided[i].getPoints();
    			
    			for(Entity e : entities) {
    				if(e.entityType == EntityType.Tile && e != other) {
    					Line[] lines = Entity.getOutline(e.hitbox);
    					
    					for(int j = 0; j < lines.length; j++) {
    						float[] line2 = lines[j].getPoints();
    						
    						float pointDiff = Math.abs(line1[0] - line2[0]) + Math.abs(line1[1] - line2[1]) + Math.abs(line1[2] - line2[2]) + Math.abs(line1[3] - line2[3]);
    						float pointDiff2 = Math.abs(line1[0] - line2[2]) + Math.abs(line1[1] - line2[3]) + Math.abs(line1[2] - line2[0]) + Math.abs(line1[3] - line2[1]);
    						if(pointDiff <= .001f || pointDiff2 <= .0001f) {
    							//System.out.println("Ignore");
    							ignore[i] = true;
    						}
    					}
    				}
    			}
    		}
    		
    		
    		
    		//Calculates the result of collision
    		for(int i = 0; i < collisions; i++) {
    			
    			if(ignore[i] == false) {
	    			
	        		float forceScalar = .008f * mass;
	        		
	    			Vector2f surface = lineToVector(collided[i]);
	    			
	    			Vector2f normal = surface.getPerpendicular();
	    			
	    			Vector2f tileCenter = other.midpoint;
	    			
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
	    			
	    			//System.out.println("Velocity on hit: "+velocity);
	    		    		
	    			Vector2f bounceDir = getReflectionVector(velocity, normal);
	    			
	    			//System.out.println("Normal Force: "+forceNormal);
	    			
	    			//Bump the ball out
	    			
	    			if((normalDir.x > 0 && normal.x < 0) || (normalDir.x < 0 && normal.x > 0)) {
	    				normal.x *= -1;
	    			}
	    			
	    			if((normalDir.y > 0 && normal.y < 0) || (normalDir.y < 0 && normal.y > 0)) {
	    				normal.y *= -1;
	    			}
	    			
	    			bounceDir.scale(forceScalar);
	    			
	    			normal.normalise();
	    			Vector2f positionReset = new Vector2f();
	    			
	    			//+0.1f prevents issues with checking for new collisions, and makes movement smoother
	    			positionReset =  normal.scale((hitbox.getBoundingCircleRadius() - 
	    										  getPerpendicularDistance(collided[i], 
	    										  hitbox.getCenterX(), hitbox.getCenterY())) + 0.1f);
	    			//System.out.println(positionReset);
	    			
	    			velocity = bounceDir;
	    			position.x += positionReset.x;
	    			position.y += positionReset.y;
	    			hitbox.setX(position.x);
	    			hitbox.setY(position.y);
    			}
    		}
    		
    		for (Entity e : entities) {
        		if(e != this && e != other) {
        			if(hitbox.intersects(e.hitbox)) {
        				onCollide(e, entities);
        				return;
        			}
        		}
        	}
    	}
    	
    	//**------------Ball Collision-------------**//
    	
    	if(other.entityType == EntityType.Ball) {
    		//Bumps the ball away
    		Vector2f positionReset = new Vector2f();
    		positionReset.x = hitbox.getCenterX() - other.hitbox.getCenterX();
    		positionReset.y = hitbox.getCenterY() - other.hitbox.getCenterY();
    		
    		positionReset.normalise();
    		positionReset.scale((float) (hitbox.getBoundingCircleRadius() - distanceTo(other)));
    		
    		//Adds a new bounce force
    		Vector2f force = new Vector2f();
    		force.x = position.x - other.getX();
    		force.y = position.y - other.getY();
    		position.x += positionReset.x;
    		position.y += positionReset.y;
    		//applyForce(force.x, force.y);
    	}
    	
    	//**------------GamePiece Collision-------------**//
    	
    	if(other.entityType == EntityType.GamePiece) {
    		if(other instanceof ConveyerBelt) {
    			ConveyerBelt belt = (ConveyerBelt)other;
    			belt.CollideAction(this);
    		}
    	}
    	
    }
	
	
}