package tile;

import entity.Entity;
import world.EntityWorld;

public class Tile extends Entity {
	
	public enum TileType {
		GenericTile
	}
	
	Tile(int x, int y, EntityWorld world) {
		super(x, y, world);
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO Tile update
		return true;
	}
	
	// TODO 
	public double getCollisionRadius() {
		return 20;
	}
	
}
