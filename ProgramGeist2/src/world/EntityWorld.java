package world;

import java.util.*;

import main.Camera;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;

import codeBlock.CodeBlock;
import codeBlock.printlnBlock;
import tile.Tile;
import tile.TriangleTile;
import entity.*;
import entity.Entity.EntityType;
import gui.CatalogMenu;

public class EntityWorld {
	public List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> newEntities = new ArrayList<Entity>();
    private List<Entity> particles = new ArrayList<Entity>();
    private List<Entity> newParticles = new ArrayList<Entity>();
    private List<CodeBlock> blocks = new ArrayList<CodeBlock>();
    private List<CodeBlock> newBlocks = new ArrayList<CodeBlock>();
	
	private double width;
	private double height;
	
	private long time;
	
	private int resource1;
	
	private Camera camera;
	
	private CatalogMenu catalogMenu;
	private boolean Edown = false;
	
	public EntityWorld(Camera c) throws SlickException {
		Random random = new Random();
		
		camera = c;
		
		addEntity(new Ball(300f, 50f, this, 100.0f));
		
		for(int i = 0; i<10; i++) {
			addEntity(new Tile(100*i, 400, this));
		}
		
		addEntity(new TriangleTile(700, 300, this));
		
		// end test code
		
		CodeBlock add = new printlnBlock(300, 200, this);
		
		addEntity(add);
		addEntity(new printlnBlock(500, 200, this));
		addEntity(new printlnBlock(300, 400, this));
		
		CatalogMenu cm = new CatalogMenu(this);
		
		catalogMenu = cm;
		
		//TODO remove test code
	}
    
	
	
    public void update(GameContainer gc, int deltaMS) {
    	time += deltaMS;
    	Input input = gc.getInput();
    	
    	updateEntityList(deltaMS, entities,  newEntities, gc);
    	updateEntityList(deltaMS, particles, newParticles, gc);
    	updateEntityList(deltaMS, blocks, newBlocks, gc, true);
    	
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
            
            if(entity.getEntityType() == EntityType.CodeBlock || entity.getEntityType() == EntityType.Object ||
            	entity.getEntityType() == EntityType.Ball) {
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
    private void updateEntityList(int deltaMS, List<CodeBlock> blocks, List<CodeBlock> newBlocks, GameContainer gc, boolean b) {
    	Iterator<CodeBlock> e = blocks.iterator();
        while (e.hasNext()) {
            CodeBlock entity = e.next();
            
            boolean entityAlive;
            
            entityAlive = entity.update(deltaMS, gc.getInput(), blocks);
            
            if (!entityAlive || entity.isRemoved()) {
                entity.setRemoved();
                e.remove();
            }
        }
        blocks.addAll(newBlocks);
        newBlocks.clear();
    }
    
    public void render(GameContainer gc, Graphics g, double camX, double camY) {
    	catalogMenu.render(gc, g);
    	
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

    	drawHitboxes(entities, g);
        

    	drawHitboxes(entities, g);
        
        catalogMenu.render(gc, g);
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
