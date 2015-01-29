
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

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

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
	protected Vector2f position = new Vector2f(0, 0);
    protected double frictionCoeffecient = 0.1;
    protected int team;
    protected int id;
    private static int nextId = 0;
    protected EntityType entityType = EntityType.GenericEntity;
    protected EntityWorld world;
    protected Image image;
    private Polygon hitbox;
    private Circle radius;
    protected boolean isCircle = false;
    
    // Constructors
    public Entity(Polygon hitbox, Circle radius, boolean circle, EntityWorld world) throws SlickException {
    	this(0, 0, hitbox, radius, circle, world);
    	this.id = ++nextId;
    }
    public Entity(float x, float y, Polygon hitbox, Circle radius, boolean circle, EntityWorld world) {
    	this.position.x = x;
    	this.position.y = y;
    	this.hitbox = hitbox;
    	this.radius = radius;
    	this.world = world;
    	this.isCircle = circle;
    	
    	try {
    	image = new Image("res/Whoops.png");
    	} catch (SlickException e) {
    		e.printStackTrace();
    	}
    }
    
    public EntityType getType() {
    	return entityType;
    }
    
    public static Polygon makeRectangle(float x, float y, float width, float height) {
    	Polygon rectangle = new Polygon();
    	Vector2f center = new Vector2f (x, y);
    	return rectangle;
    }
    
    public boolean isFixedPosition() {
    	return false;
    }
    
    // Entities will have their own update code
    public abstract boolean update(int deltaMS);
    
    
    public void render(Graphics g, double camX, double camY) {
    	g.drawImage(image, (float)(position.x-camX), (float)(position.y-camY));
    }
    public float getX() {
    	return position.x;
    }
    
    public float getY() {
    	return position.y;
    }
    
    // Get distance from entity other
    public double distanceTo(Entity other) {
        double dx = position.x - other.position.x;
        double dy = position.y - other.position.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    // returns the perspective xy plane distance from entity other
    public double perspectiveDistanceToSqr(Entity other) {
        double dx = position.x - other.position.x;
        double dy = (position.y - other.position.y) * 2;
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

// // check if entity has collided with anything
//    public void checkCollisions(List<Entity> entities) {
//        double thisRadius = getCollisionRadius();
//        for (Entity e : entities) {
//            if (collidesWith(e) && e.collidesWith(this)) {
//                double radius = thisRadius + e.getCollisionRadius();
//                if (distanceTo(e) < radius) {
//                    onCollide(e);
//                }
////                // positions may have changed, so recalculate
////                if (perspectiveDistanceToSqr(e) < radius * radius) {
////                    e.onCollide(this);
////                }
//            }
//        }
//    }
    
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

    
    public boolean collidesWith(Entity other) {
        return other != this;
    }
    
    protected void onCollide(Entity entity) {
    }
    
    public double getCollisionRadius() {
        return 16;
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
