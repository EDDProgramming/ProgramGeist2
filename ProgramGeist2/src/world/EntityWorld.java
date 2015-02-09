package world;

import java.util.*;

import main.Camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import codeBlock.StackBlock;
import tile.Tile;
import entity.*;
import entity.Entity.EntityType;
import gui.CatalogMenu;

public class EntityWorld {
	public List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> newEntities = new ArrayList<Entity>();
    private List<Entity> particles = new ArrayList<Entity>();
    private List<Entity> newParticles = new ArrayList<Entity>();
	
	private double width;
	private double height;
	
	private long time;
	
	private int resource1;
	
	private Camera camera;
	
	private CatalogMenu catalogMenu = new CatalogMenu();
	private boolean Edown = false;
	
	public EntityWorld(Camera c) throws SlickException {
		Random random = new Random();
		
		camera = c;
		
		//TODO remove test code
		
		addEntity(new Ball(300f, 50f, this, 100.0f));
		
		for(int i = 0; i<10; i++) {
		addEntity(new Tile(100*i, 400, this));
		
		}
		
		// end test code
		
		addEntity(new StackBlock(300, 200, this));
		
	}
    
    public void update(GameContainer gc, int deltaMS) {
    	time += deltaMS;
    	Input input = gc.getInput();
    	
    	updateEntityList(deltaMS, entities,  newEntities, gc);
    	updateEntityList(deltaMS, particles, newParticles, gc);
    	
    	if(catalogMenu.isVisible()) {
    		catalogMenu.update(gc, deltaMS);
    	}
    	
    	if(Edown == true) {
    		if(!input.isKeyDown(Input.KEY_E)) {
    			Edown = false;
    		}
    	}
    	if(input.isKeyDown(Input.KEY_E) && Edown == false) {
    		catalogMenu.setVisible(!catalogMenu.isVisible());
    		Edown = true;
    	}
    }
    
    // getEntitiesInRange
    //
    // returns an array of the entities in the given range
    //
    // parameters:
    //  x coordinate of the top left corner of the range
    //  y coordinate of the top left corner of the range
    //  width of the range
    //  height of the range
    public Entity[] getEntitiesInRange(double x, double y, double width, double height) {
    	Entity[] out = null;
    	ArrayList<Entity> temp = new ArrayList<Entity>();
    	Iterator<Entity> e = entities.iterator();
    	while(e.hasNext()) {
    		Entity entity = e.next();
    		if(entity.getX() >= x && entity.getX() <= x+width && entity.getY() >= y && entity.getY() <= y+height ) {
    			temp.add(entity);
    		}
    	}
    	out = new Entity[temp.size()];
    	for(int i = 0; i<temp.size(); i++) {
    		out[i] = temp.get(i);
    	}
    	return out;
    }
    
    private void updateEntityList(int deltaMS, List<Entity> ents, List<Entity> newEnts, GameContainer gc) {
    	Iterator<Entity> e = ents.iterator();
        while (e.hasNext()) {
            Entity entity = e.next();
            
            boolean entityAlive;
            
            if(entity.getEntityType() == EntityType.CodeBlock || entity.getEntityType() == EntityType.Object) {
            	entityAlive = entity.update(deltaMS, gc.getInput());
            }else {
            	entityAlive = entity.update(deltaMS);
            }
            
            if (!entityAlive || entity.isRemoved()) {
                entity.setRemoved();
                e.remove();
            }
        }
        ents.addAll(newEnts);
        newEnts.clear();
    }
    
    public void render(GameContainer gc, Graphics g, double camX, double camY) {
    	ArrayList<Entity> renderableEntities = new ArrayList<Entity>();
        renderableEntities.addAll(entities);
        renderableEntities.addAll(particles);
        
        Iterator<Entity> iterator = renderableEntities.iterator();
        while (iterator.hasNext()) {
            Entity r = iterator.next();
            r.render(g, camX, camY);
            iterator.remove();
        }
        
        catalogMenu.render(gc, g);
    }
    
    public double[] getPathing(Entity ent, Entity target) {
    	double[] out = new double[2];
    	// ???
    	
    	// TEMPORARY
    	out[0] = target.getX();
    	out[1] = target.getY();
    	// TEMPORARY
    	
    	return out;
    }
    
    public boolean isGameOver() {
    	return false;
    }
    
    
    public long getTime() {
    	return time;
    }
    
    public int getResources() {
    	return resource1;
    }
    
    public int addResources(int res1) {
    	resource1+=res1;
    	return resource1;
    }
    
    public void addEntity(Entity e) {
        newEntities.add(e);
    }
    
    public void addParticle(Entity e) {
        newParticles.add(e);
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
}
