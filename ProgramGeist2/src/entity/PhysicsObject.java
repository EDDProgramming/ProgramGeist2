package entity;

import world.EntityWorld;
import codeBlock.CodeBlock;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;


public class PhysicsObject extends Entity {
	
	//Signifies that code can be applied to this object
	protected boolean isEditable  = true;
	//Signifies that this is the goal
	protected boolean isObjective = false;
	//Signifies that this is the gamepiece that must go to the goal
	protected boolean isPlayer    = false;
	
	protected ArrayList<CodeBlock> code = new ArrayList<CodeBlock>(); // Code controlling this object in game
	
	float mass = 1;
	protected Vector2f sumForce = new Vector2f(0, 0);
	protected Vector2f acceleration = new Vector2f(0, 0);
    protected Vector2f velocity = new Vector2f(0, 0);
    
    //Used for collisions, saves at the end of every frame
    protected Vector2f prevPosition = new Vector2f(0, 0);
	

	public PhysicsObject(float x, float y, Polygon hitbox, Circle radius, boolean circle, EntityWorld world, float mass) throws SlickException {
		super(x, y, hitbox, radius, circle, world);
		
		this.mass = mass;
		
		entityType = EntityType.Object;
	}

	@Override
	public boolean update(int deltaMS) {
		return !removed;
	}
    
	//Reset step of physics update, for use in subclasses
	public void updateInit() {
		sumForce.x = 0;
		sumForce.y = 0;

		hitbox.setCenterX(position.x);
		hitbox.setCenterY(position.y);
		radius.setCenterX(position.x);
		radius.setCenterY(position.y);
	}
	
	//Regular force checks of physics update, for use in subclasses
	public void updateForces(float friction) {
		applyGravity();
		applyFriction(friction);
		checkCollisions(world.entities);
	}
	
	//Calculates acceleration, velocity and position based on force, plus some cleanup.
	public void updatePosition() {
		if(mass != 0) {
			acceleration.x = sumForce.x / mass;
			acceleration.y = sumForce.y / mass;
		}
			
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		position.x += velocity.x;
		position.y += velocity.y;
		
		
		//Keep this at the end. Is used to get the position of the object in the previous frame.
		
		prevPosition.x = position.x;
		prevPosition.y = position.y;
	}
	
	//Force in an X and Y Vector pair
    public void applyForce(float forceX, float forceY) {
        sumForce.x += forceX;
        sumForce.y += forceY;
    }
    
    //Force with a direction and magnitude
    public void applyForce(double deg, double magnitude) {
    	float forceX;
    	float forceY;
    	
    	forceX = (float) (magnitude * Math.sin(deg));
    	forceY = (float) (magnitude * Math.cos(deg));
    	
    	sumForce.x += forceX;
    	sumForce.y += forceY;
    }
    
    public void applyGravity() {
    	
    	float magnitude;
    	
    	magnitude = (float) (mass * .98);
    	
    	applyForce(0, magnitude);
    	//Positive is down
    }
    
    // Slows down an object
    // ONLY WORKS WITH AIR FRICTION
    public void applyFriction(float MU) {
    	// Fair = -MU*v^2/2
    	// Fdrag = 1/2p*v^2*C*A
    	float forceX = -MU * velocity.x;
    	float forceY = -MU * velocity.y;
    	applyForce(forceX, forceY);
    }
    
    public void setObjective() {
    	isObjective = true;
    }
    
    public void setPlayer() {
    	isPlayer = true;
    }
    
    public void checkCollisions(List<Entity> entities) {
    	for (Entity e: entities) {
    		if(isCircle) {
    			//If both entities are circles
    			if(e.isCircle && radius.intersects(e.radius)) {
    				onCollide(e);
    			}
    			
    			//If only this is a circle
    			else if(!e.isCircle && radius.intersects(e.hitbox)) {
    				onCollide(e);
    			}	
    		}
    		
    		else if(!isCircle) {
    			//If only other is a circle
    			if(e.isCircle && hitbox.intersects(e.radius)) {
    				onCollide(e);
    			}
    			
    			//If both are polygons
    			else if(!e.isCircle && hitbox.intersects(e.hitbox)) {
    				onCollide(e);
    			}
    		}
    	}
    }
    
    @Override
    protected void onCollide(Entity other){};
    
    protected void onCollide(PhysicsObject other) {
    	// TODO test if this is ever called by world. The code might not register that what it is contacting is a physics object as well as an entity.
    	if(isPlayer) {
    		if(other.isObjective()) {
    			// TODO create win screen
    			System.out.println("YOU WIN!");
    		}
    	}
    }
	
	//
	// Get methods
	//
	public boolean isEditable() {
		return isEditable;
	}
    
    public double getEntityHeight() {
        return 32.0f;
    }
	
    public boolean isObjective() {
    	return isObjective;
    }
    
    public boolean isPlayer() {
    	return isPlayer;
    }
    
    
}
