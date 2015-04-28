package entity;

import world.EntityWorld;
import codeBlock.CodeBlock;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import de.lessvoid.nifty.Nifty;


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
	

	public PhysicsObject(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, world);
		
		this.mass = mass;
		
		entityType = EntityType.Object;
	}
	
	public boolean update(int deltaMS, Input input, Nifty nifty) {
		return !removed;
	}
    
	//Reset step of physics update, for use in subclasses
	public void updateInit() {
		sumForce.x = 0;
		sumForce.y = 0;

		hitbox.setX(position.x);
		hitbox.setY(position.y);
	}
	
	//Regular force checks of physics update, for use in subclasses
	public void updateForces(float friction) {
		applyGravity();
		applyFriction(friction);
		checkCollisions(world.getEntities());
	}
	
	//Calculates acceleration, velocity and position based on force, plus some cleanup.
	public void updatePosition(float deltaMS) {
		
		//Calculate the acceleration based on F = MA
		if(mass != 0) {
			acceleration.x = sumForce.x / mass;
			acceleration.y = sumForce.y / mass;
		}
		
		//Increment the velocity by acceleration
		velocity.x += acceleration.x * deltaMS / 300;
		velocity.y += acceleration.y * deltaMS / 300;
		
		//Move the object based on its current velocity
		position.x += velocity.x * deltaMS;
		position.y += velocity.y * deltaMS;
		
		
		//Keep this at the end. Is used to get the position of the object in the previous frame.
		
		prevPosition.x = position.x;
		prevPosition.y = position.y;
	}

	//Force in an X and Y Vector pair
    public void applyForce(float forceX, float forceY) {
        sumForce.x += forceX;
        sumForce.y += forceY;
    }

    //Direction doesn't work right, removed.
    
    /*
    //Force with a direction and magnitude
    //0 degrees is up
    //90 degrees is right
    public void applyForce(float deg, int magnitude) {
    	float forceX;
    	float forceY;
    	
    	forceX =  (float) (magnitude * Math.sin(deg));
    	forceY = -(float) (magnitude * Math.cos(deg));
    	
    	sumForce.x += forceX;
    	sumForce.y += forceY;
    }
    */
 
    public void applyGravity() {
    	
    	float magnitude;
    	
    	magnitude = (float) (mass * .20);
    	
    	applyForce(0.0f, magnitude);
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
    	for (Entity e : entities) {
    		if(e != this) {
    			if(hitbox.intersects(e.hitbox)) {
    				onCollide(e, entities);
    				return;
    			}
    		}
    	}
    }
    
    @Override
    protected void onCollide(Entity other, List<Entity> entities){};
    
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