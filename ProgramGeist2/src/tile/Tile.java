package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entity.Entity;
import world.EntityWorld;

public class Tile extends Entity {
	
	public enum TileType {
		GenericTile
	}
	
	public Tile(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		image = new Image("res/CrappyTile.png");
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO Tile update
		return true;
	}

	@Override
	public double getCollisionRadius() {
		return 50; 
		// assuming the tile is square. 
		//We will run into problems if we want to make any that are not square.
	}
	
}
