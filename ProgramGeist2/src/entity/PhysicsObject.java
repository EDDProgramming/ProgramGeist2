package entity;

import world.EntityWorld;
import codeBlock.CodeBlock;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.SlickException;


public class PhysicsObject extends Entity {
	
	protected boolean isEditable  = true;
	protected boolean isObjective = false;
	protected boolean isPlayer    = false;
	
	protected ArrayList<CodeBlock> code = new ArrayList<CodeBlock>(); // Code controlling this object in game
	
	float mass;
	protected Vector2f acceleration = new Vector2f(0, 0);
    protected Vector2f velocity = new Vector2f(0, 0);
    protected double frictionCoeffecient = 0.1; // MU for EVERYTHING. Probably not a good idea.
	
	public PhysicsObject(double x, double y, EntityWorld world, float mass) throws SlickException {
		super(x, y, world);
		
		entityType = EntityType.Object;
	}

	@Override
	public boolean update(int deltaMS) {
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		return false;
	}
    
    public void applyForce(float forceX, float forceY) {
        acceleration.x += forceX / mass;
        acceleration.y += forceY / mass;
    }
    
    public void applyForce(double deg, double magnitude) {
    	float forceX;
    	float forceY;
    	
    	forceX = (float) (magnitude * Math.sin(deg));
    	forceY = (float) (magnitude * Math.cos(deg));
    	
    	acceleration.x += forceX / mass;
    	acceleration.y += forceY / mass;
    }
    
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
