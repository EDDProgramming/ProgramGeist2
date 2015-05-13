package world;

import java.util.*;

import main.Camera;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import codeBlock.CodeBlock;
import de.lessvoid.nifty.Nifty;
import entity.*;

public class EntityWorld {
	public List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> newEntities = new ArrayList<Entity>();
    private List<Entity> particles = new ArrayList<Entity>();
    private List<Entity> newParticles = new ArrayList<Entity>();
    protected List<CodeBlock> blocks = new ArrayList<CodeBlock>();
    private List<CodeBlock> newBlocks = new ArrayList<CodeBlock>();
    
	private long time;
	
	private int resource1;
	
	public Nifty nifty;
	
	private Camera camera;
	
	public EntityWorld(Camera c, Nifty n) throws SlickException {
		camera = c;
		nifty = n;
	}
    
	
	
    public void update(GameContainer gc, int deltaMS) {
    	time += deltaMS;
    	
    	updateEntityList(deltaMS, entities,  newEntities, gc, nifty);
    	updateEntityList(deltaMS, particles, newParticles, gc, nifty);
    	updateEntityList(deltaMS, blocks, newBlocks, gc, true, nifty);
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
    		if(entity.getX() >= x && entity.getX() <= x + width && entity.getY() >= y
    				&& entity.getY() <= y + height ) {
    			temp.add(entity);
    		}
    	}
    	out = new Entity[temp.size()];
    	for(int i = 0; i<temp.size(); i++) {
    		out[i] = temp.get(i);
    	}
    	return out;
    }
    
    private void updateEntityList(int deltaMS, List<Entity> ents, List<Entity> newEnts, GameContainer gc, Nifty nifty) {
    	Iterator<Entity> e = ents.iterator();
        while (e.hasNext()) {
            Entity entity = e.next();
            
            boolean entityAlive;
            
            entityAlive = entity.update(deltaMS, gc.getInput(), nifty);
            
            if (!entityAlive || entity.isRemoved()) {
                entity.setRemoved();
                e.remove();
            }
        }
        ents.addAll(newEnts);
        newEnts.clear();
    }
    
    private void updateEntityList(int deltaMS, List<CodeBlock> blocks, List<CodeBlock> newBlocks, GameContainer gc, boolean b, Nifty nifty) {
    	Iterator<CodeBlock> e = blocks.iterator();
        while (e.hasNext()) {
            CodeBlock entity = e.next();
            
            boolean entityAlive;
            
            entityAlive = entity.update(deltaMS, gc.getInput(), blocks, nifty);
            
            if (!entityAlive || entity.isRemoved()) {
                entity.setRemoved();
                e.remove();
            }
        }
        blocks.addAll(newBlocks);
        newBlocks.clear();
    }
    
    public void render(GameContainer gc, Graphics g, double camX, double camY) throws SlickException {
    	standardRender(gc, g, camX, camY);
    	drawHitboxes(entities, g);
    	drawHitboxes(blocks, g, true);

    }
    
    public void standardRender(GameContainer gc, Graphics g, double camX, double camY) throws SlickException {
    	ArrayList<Entity> renderableEntities = new ArrayList<Entity>();
        renderableEntities.addAll(entities);
        renderableEntities.addAll(particles);
        renderableEntities.addAll(blocks);
        
        Iterator<Entity> iterator = renderableEntities.iterator();
        while (iterator.hasNext()) {
            Entity r = iterator.next();
            r.render(g, camX, camY);
            iterator.remove();
        }
    }
    
    
    //For rendering hitboxes
    public void drawHitboxes(List<Entity> entities, Graphics g) {
    	Iterator<Entity> iterator = entities.iterator();
    	while (iterator.hasNext()) {
    		Entity r = iterator.next();
    		g.setColor(Color.red);
    		g.draw(r.hitbox);
    	}
    }
    public void drawHitboxes(List<CodeBlock> entities, Graphics g, boolean b) {
    	Iterator<CodeBlock> iterator = entities.iterator();
    	while (iterator.hasNext()) {
    		Entity r = iterator.next();
    		g.setColor(Color.red);
    		g.draw(r.hitbox);
    	}
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
    public void addEntity(CodeBlock e) {
    	newBlocks.add(e);
    }
    
    public void addParticle(Entity e) {
        newParticles.add(e);
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
}
