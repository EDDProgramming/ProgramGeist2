package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.lessvoid.nifty.Nifty;
import entity.Entity;
import world.EntityWorld;

public class TriangleTile2 extends Entity {
	
	public TriangleTile2(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		hitbox = makeTriangle(x + 50, y + 50, -50, 50, 50, 50, -50, -50);
		midpoint = new Vector2f(position.x + 25, position.y + 75);
		//Slope faces up-right
		currentImage = new Image("res/TriangleTile.png");
		currentImage = currentImage.getFlippedCopy(true, false);
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS, Input input, Nifty nifty) {
		// TODO Auto-generated method stub
		return true;
	}

	
}
