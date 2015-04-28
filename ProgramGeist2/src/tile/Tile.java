package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.lessvoid.nifty.Nifty;
import entity.Entity;
import world.EntityWorld;

public class Tile extends Entity {
	
	public enum TileType {
		GenericTile
	}
	
	public Tile(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		hitbox = makeRectangle(x + 50, y + 50, 100, 100);
		currentImage = new Image("res/BetterTile.png");
		
		entityType = EntityType.Tile;
	}
	
	@Override
	public boolean update(int deltaMS, Input input, Nifty nifty) {
		// TODO Auto-generated method stub
		return true;
	}
}
