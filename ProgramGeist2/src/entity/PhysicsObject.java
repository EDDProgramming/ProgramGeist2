package entity;

import world.EntityWorld;
import codeBlock.CodeBlock;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.SlickException;


public class PhysicsObject extends Entity {
	
	protected boolean isEditable  = true;
	protected boolean isObjective = false;
	protected boolean isPlayer    = false;
	
	protected ArrayList<CodeBlock> code = new ArrayList<CodeBlock>(); // Code controlling this object in game
	
	protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected Vector3f velocity = new Vector3f(0, 0, 0);
    protected double frictionCoeffecient = 0.1; // MU for EVERYTHING. Probably not a good idea.
	
	public PhysicsObject(double x, double y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		entityType = EntityType.Object;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO PO update
		return false;
	}
    
    public void push(Vector3f push) {
        velocity.x += push.x;
        velocity.y += push.y;
        velocity.z += push.z;
    }
    
    public void applyFriction(double MU) {
    	velocity.x -= velocity.x * MU;
    	velocity.y -= velocity.y * MU;
    	velocity.z -= velocity.z * MU;
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
    
    public void resolveCollisionWithFixedEntity(Entity entity) {
//      double radius = getCollisionRadius() + entity.getCollisionRadius();
//      // push away... or something like that
//      double dx = (entity.x - x);
//      double dy = (entity.y - y) * 2;
//      double dist = Math.sqrt(dx * dx + dy * dy);
//      x = entity.x - dx / dist * radius;
//      y = entity.y - dy / dist * radius * .5f;
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
