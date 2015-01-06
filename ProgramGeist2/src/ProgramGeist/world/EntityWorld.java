package ProgramGeist.world;

import java.util.*;

import ProgramGeist.entity.*;
//import ProgramGeist.gui.*;


public class EntityWorld {
	private List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> newEntities = new ArrayList<Entity>();
    private List<Entity> particles = new ArrayList<Entity>();
    private List<Entity> newParticles = new ArrayList<Entity>();
	
	private double width;
	private double height;
	
	private long time;
	
	private int resource1;
	
	public EntityWorld() {
		Random random = new Random();
		//addEntity(new WorkerBee(10, 10, 0, 0, this));
	}
    
    public void update(int deltaMS) {
    	time += deltaMS;
    	
    	updateEntityList(deltaMS, entities,  newEntities,  true);
    	updateEntityList(deltaMS, particles, newParticles, false);
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
    
    private void updateEntityList(int deltaMS, List<Entity> ents, List<Entity> newEnts, boolean checkCollisions) {
    	Iterator<Entity> e = ents.iterator();
        while (e.hasNext()) {
            Entity entity = e.next();

            if (!entity.update(deltaMS) || entity.isRemoved()) {
                entity.setRemoved();
                e.remove();
            } else if (checkCollisions) {
                entity.checkCollisions(entities);
            }
        }
        ents.addAll(newEnts);
        newEnts.clear();
    }
    
    public void render(double camX, double camY) {
    	ArrayList<Entity> renderableEntities = new ArrayList<Entity>();
        renderableEntities.addAll(entities);
        renderableEntities.addAll(particles);
        
        Iterator<Entity> iterator = renderableEntities.iterator();
        while (iterator.hasNext()) {
            Entity r = iterator.next();
//            r.render(screen, camX, camY);
            iterator.remove();
        }
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
