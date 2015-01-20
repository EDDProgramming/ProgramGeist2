package entity;

import world.EntityWorld;
import codeBlock.CodeBlock;

import java.util.ArrayList;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.SlickException;


public class PhysicsObject extends Entity {
	
	//Signifies that code can be applied to this object
	protected boolean isEditable  = true;
	//Signifies that this is the goal
	protected boolean isObjective = false;
	//Signifies that this is the gamepiece that must go to the goal
	protected boolean isPlayer    = false;
	
	protected ArrayList<CodeBlock> code = new ArrayList<CodeBlock>(); // Code controlling this object in game
	
	float mass = 1; 
	protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected Vector3f velocity = new Vector3f(0, 0, 0);
	

	public PhysicsObject(float x, float y, EntityWorld world, float mass) throws SlickException {
		super(x, y, world);
		
		this.mass = mass;
		
		entityType = EntityType.Object;
	}

	@Override
	public boolean update(int deltaMS) {
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		position.x += velocity.x;
		position.y += velocity.y;
		
		return !removed;
	}
    
	
	//Force in an X and Y Vector pair
    public void applyForce(float forceX, float forceY) {
        acceleration.x += forceX / mass;
        acceleration.y += forceY / mass;
    }
    
    //Force with a direction and magnitude
    public void applyForce(double deg, double magnitude) {
    	float forceX;
    	float forceY;
    	
    	forceX = (float) (magnitude * Math.sin(deg));
    	forceY = (float) (magnitude * Math.cos(deg));
    	
    	acceleration.x += forceX / mass;
    	acceleration.y += forceY / mass;
    }
    
    public void applyGravity() {
    	applyForce(0f, 0.1f); // Remember that positives are down!
    }
    
    // Slows down an object
    // ONLY WORKS WITH AIR FRICTION
    public void applyFriction(double MU) {
    	// Fair = -MU*v^2/2
    	// Fdrag = 1/2p*v^2*C*A
    	float forceX = (float) (-MU * (velocity.x*velocity.x)/ 2);
    	float forceY = (float) (-MU * (velocity.y*velocity.y)/ 2);
    	applyForce(forceX, forceY);
    }
    
    public void setObjective() {
    	isObjective = true;
    }
    
    public void setPlayer() {
    	isPlayer = true;
    }
    
    @Override
    protected void onCollide(Entity other) {
    	if(other.entityType == entityType.Tile) {
    		applyForce(0f, 1/2 * mass * (velocity.y*velocity.y)); // impact force
    		position.y = (float) ((float) (other.position.y-other.getCollisionRadius())-this.getCollisionRadius()-3);
    	}
    	
    }
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
