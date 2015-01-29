package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import entity.Entity;
import world.EntityWorld;

public class Tile extends Entity {
	
	public enum TileType {
		GenericTile
	}
	
	static Circle radius = new Circle(0, 0, 50);
	
	public Tile(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, makeRectangle(x, y, 50, 50), radius, false, world);
		
		image = new Image("res/CrappyTile.png");
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO Tile update
		return true;
	}

	
}
