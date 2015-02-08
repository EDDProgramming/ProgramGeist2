
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
    protected boolean removed = false;
	protected Vector2f position = new Vector2f(0, 0);
    protected double frictionCoeffecient = 0.1;
    protected int team;
    protected int id;
    private static int nextId = 0;
    protected EntityType entityType = EntityType.GenericEntity;
    protected EntityWorld world;
    protected Image image;
    protected Polygon hitbox;
    protected Circle radius;
    protected boolean isCircle = false;
    
    // Constructors
    public Entity(Polygon hitbox, Circle radius, boolean circle, EntityWorld world) throws SlickException {
    	this(0, 0, hitbox, radius, circle, world);
    	this.id = ++nextId;
    }
    public Entity(float x, float y, Polygon hitbox, Circle circle, boolean isCircle, EntityWorld world) {
    	this.position.x = x;
    	this.position.y = y;
    	this.hitbox = hitbox;
        hitbox.setClosed(true);
    	this.radius = circle;
    	this.world = world;
    	this.isCircle = isCircle;
    	
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
    	Polygon rectangle;
    	width /= 2;
    	height /= 2;
    	float[] coordinates = {	x + width, y + height, x + width, y - height, 
    							x - width, y- height, x - width, y - height};
    	rectangle = new Polygon(coordinates);
    	return rectangle;
    }
    
    public boolean isFixedPosition() {
    	return false;
    }
    
    // Entities will have their own update code
    public abstract boolean update(int deltaMS);
    
    public abstract boolean update(int deltaMS, Input input);
    
    public void render(Graphics g, double camX, double camY) {
    	g.drawImage(image, (float)(position.x-camX), (float)(position.y-camY));
    }
    public float getX() {
    	return position.x;
    }
    
    public float getY() {
    	return position.y;
    }
    
    //Get distance from entity other
    public double distanceTo(Entity other) {
        double dx = position.x - other.position.x;
        double dy = position.y - other.position.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    //Get the lines comprising the hitbox
    public static Line[] getOutline(Polygon hitbox) {
    	
    	//Get a list of points making up the polygon in the form x0, y0... xn, yn
		float[] points = hitbox.getPoints();
		
		//Set the number of lines that we will get to be the number of coordinate pairs in the shape.
		int length = points.length / 2;
		
		//Create a new array of Lines to be created
		Line[] outline = new Line[length];
		
		//i is increased by 2 each time to increment by one coordinate pair
		//When i is equal to or greater than the number of points in the array, this loop ends
		//This will return all of the lines except the one connecting the last point to the first one.
		for(int i = 3, j = 0; i < points.length; i += 2, j++) {
			
			//i-3 = x0, i-2 = y0, i-1 = x1, i = y1
			outline[j] = new Line(points[i-3], points[i-1], points[i-2], points[i]);
		}
		
		//This gets the last line
		int lastX = points.length - 2;
		int lastY = points.length - 1;
		
		outline[length - 1] = new Line(points[lastX], points[1], points[lastY], points[2]);
    	
    	return outline;
    }
    
    //Convert a line into a vector
    public static org.newdawn.slick.geom.Vector2f lineToVector(Line line) {
    	//1 = x0, 2 = y0, 3 = x1, 4 = y1
    	float[] points = line.getPoints();
    	Vector2f vector = new Vector2f(points[3] - points[1], points[4] - points[2]);
    	
    	return vector;
    }
    
    public static Vector2f getReflectionVector(Vector2f dir, Vector2f reflector) {
    	//Steps of what is going on here
    	//1. Get the dot product of 2 * dir and reflector
    	//2. Divide this value by the squared magnitude of reflector
    	//3. Scale reflector by this value
    	//4. Subtract the new value of reflector from dir to get reflection
    	Vector2f reflection = dir.sub(reflector.scale(dir.scale(2).dot(reflector) / reflector.lengthSquared()));
    	
    	return reflection;
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
    

    
    public boolean collidesWith(Entity other) {
        return other != this;
    }
    
    protected void onCollide(Entity entity) {
    }
    
    public EntityType getEntityType() {
    	return this.entityType;
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
