
/*
 * side notes:
 * 
 * What is an entity?
 * a thing with distinct and independent existence
 * 
 * What does something need to have distinct existence in our game?
 * 	Position
 * 	Movement
 * 	Action
 * 	An image
 * 	Animation
 * 
 */

/*
 * class: Entity
 * 
 * most basic entity object, all other entities will inherit (directly or indirectly this object)
 * 
 *fields:
 * 	position:
 * 		x
 * 		y
 * 		z
 *	velocity vector
 *	acceleration vector
 *
 *methods:
 *	getX
 *	getY
 *	getZ
 *	getAcceleration
 *	getVelocity
 *
 * 
 */

package ProgramGeist.entity;

import java.util.*;

import org.lwjgl.util.vector.Vector3f;

import ProgramGeist.world.*;


public abstract class Entity {
	public enum EntityType {
		GenericEntity, Unit, RangedUnit, ProjectileEntity
	}
	
	protected static Random random = new Random();
	
	protected double x, y, z;
	
	protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected Vector3f velocity = new Vector3f(0, 0, 0);
    
    protected double frictionCoeffecient = 0.1;
    
    private boolean removed;
    
    protected int team;
    protected int id;
    private static int nextId = 0;
    
    protected EntityType entityType = EntityType.GenericEntity;
    
    protected EntityWorld world;    
    
    // Constructors
    public Entity(EntityWorld world) {
    	this(0, 0, 0, 0, world);
    	this.id = ++nextId;
    }
    public Entity(double x, double y, int team, EntityWorld world) {
    	this(x, y, 0, team, world);
    }
    public Entity(double x, double y, double z, EntityWorld world) {
    	this(x, y, z, 0, world);
    }
    public Entity(double x, double y, double z, int team, EntityWorld world) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.team = team;
    	
    	this.world = world;
    }
    
    public EntityType getType() {
    	return entityType;
    }
    
    
    public boolean isFixedPosition() {
    	return false;
    }
    
    // Entities will have their own update code
    public abstract boolean update(int deltaMS);
    
    
    public void render(double camX, double camY) {
    	
    }
    
    
    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }
    
    public double distanceToSqr(Entity other) {
        double dx = x - other.x;
        double dz = z - other.z;
        return dx * dx + dz * dz;
    }
    
    // returns the xy plane distance from entity other
    public double perspectiveDistanceToSqr(Entity other) {
        double dx = x - other.x;
        double dy = (y - other.y) * 2;
        return dx * dx + dy * dy;
    }
    
    // check if entity has collided with 
    public void checkCollisions(List<Entity> entities) {
        double thisRadius = getCollisionRadius();
        for (Entity e : entities) {
            if (collidesWith(e) && e.collidesWith(this)) {
                double radius = thisRadius + e.getCollisionRadius();
                if (perspectiveDistanceToSqr(e) < radius * radius) {
                    onCollide(e);
                }
//                // positions may have changed, so recalculate
//                if (perspectiveDistanceToSqr(e) < radius * radius) {
//                    e.onCollide(this);
//                }
            }
        }
    }
    
    public boolean collidesWith(Entity other) {
        return other != this;
    }
    
    public double getCollisionRadius() {
        return 16;
    }
    
    public double getEntityHeight() {
        return 32.0f;
    }
    
    public int getID() {
    	return id;
    }
    
    protected void onCollide(Entity entity) {
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void setRemoved() {
        this.removed = true;
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
    
    public void resolveCollisionWithFixedEntity(Entity entity) {
//        double radius = getCollisionRadius() + entity.getCollisionRadius();
//        // push away... or something like that
//        double dx = (entity.x - x);
//        double dy = (entity.y - y) * 2;
//        double dist = Math.sqrt(dx * dx + dy * dy);
//        x = entity.x - dx / dist * radius;
//        y = entity.y - dy / dist * radius * .5f;
    }
    
}
