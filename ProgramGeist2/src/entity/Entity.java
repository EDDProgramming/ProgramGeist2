
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

package entity;

import java.util.*;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.*;

import world.*;


public abstract class Entity {
	public enum EntityType {
		GenericEntity, 
		CodeBlock,
		Object,
		Tile
	}
	
	protected static Random random = new Random();
	protected double x, y, z;
    protected boolean removed = false;
    protected int id;
    private static int nextId = 0;
    protected EntityType entityType = EntityType.GenericEntity;
    protected EntityWorld world;
    protected Image image;
    
    // Constructors
    public Entity(EntityWorld world) throws SlickException {
    	this(0, 0, 0, world);
    	this.id = ++nextId;
    }
    public Entity(double x, double y, EntityWorld world) throws SlickException {
    	this(x, y, 0, world);
    }
    public Entity(double x, double y, double z, EntityWorld world) throws SlickException {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	
    	this.world = world;
    	
    	image = new Image("res/Whoops.png");
    }
    
    public EntityType getType() {
    	return entityType;
    }
    
    public boolean isFixedPosition() {
    	return false;
    }
    
    // Entities will have their own update code
    public abstract boolean update(int deltaMS);
    
    
    public void render(Graphics g, double camX, double camY) {
    	g.drawImage(image, (float)(x-camX), (float)(y-camY));
    }
    
    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }
    
    // get squared distance from entity other
    public double distanceToSqr(Entity other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return dx * dx + dy * dy;
    }
    
    // returns the perspective xy plane distance from entity other
    public double perspectiveDistanceToSqr(Entity other) {
        double dx = x - other.x;
        double dy = (y - other.y) * 2;
        return dx * dx + dy * dy;
    }
    
    public int getID() {
    	return id;
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void setRemoved() {
        this.removed = true;
    }
    
    //
    // Stuff that should probably be going into PhysicsObject
    //
    
 // check if entity has collided with anything
    public void checkCollisions(List<Entity> entities) {
        double thisRadius = getCollisionRadius();
        for (Entity e : entities) {
            if (collidesWith(e) && e.collidesWith(this)) {
                double radius = thisRadius + e.getCollisionRadius();
                if (distanceToSqr(e) < radius * radius) {
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
    
    protected void onCollide(Entity entity) {
    }
    
    public double getCollisionRadius() {
        return 16;
    }
    
}
