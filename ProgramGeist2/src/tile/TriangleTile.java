package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.lessvoid.nifty.Nifty;
import entity.Entity;
import world.EntityWorld;

public class TriangleTile extends Entity {
	
	public TriangleTile(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		hitbox = makeTriangle(x + 50, y + 50, -50, 50, 50, 50, 50, -50);
		midpoint = new Vector2f(position.x + 75, position.y + 75);
		//Slope faces up-left
		currentImage = new Image("res/TriangleTile.png");
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS, Input input, Nifty nifty) {
		// TODO Auto-generated method stub
		return true;
	}

	
}
