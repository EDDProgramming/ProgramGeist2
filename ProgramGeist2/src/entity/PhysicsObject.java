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
	
	float mass;
	protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected Vector3f velocity = new Vector3f(0, 0, 0);
    protected double frictionCoeffecient = 0.1; // MU for EVERYTHING. Probably not a good idea.
	
	public PhysicsObject(float x, float y, EntityWorld world, double d) throws SlickException {
		super(x, y, world);
		
		entityType = EntityType.Object;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO PO update
		return false;
	}
    
    @Override
	public void push(Vector3f push) {
        velocity.x += push.x;
        velocity.y += push.y;
        velocity.z += push.z;
    }
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
    
    @Override
	public void applyFriction(double MU) {
    	velocity.x -= velocity.x * MU;
    	velocity.y -= velocity.y * MU;
    }
    
    public void setObjective() {
    	isObjective = true;
    }
    
    public void setPlayer() {
    	isPlayer = true;
    }
    
    @Override
    protected void onCollide(Entity other) {
    	
    }
    protected void onCollide(PhysicsObject other) {
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
