package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import entity.Entity;
import world.EntityWorld;

public class TriangleTile2 extends Entity {
	
	public TriangleTile2(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, makeTriangle(x + 50, y + 50, -50, 50, 50, 50, -50, -50), world);
		
		//Slope faces up-right
		image = new Image("res/TriangleTile.png");
		image = image.getFlippedCopy(true, false);
		
		entityType = EntityType.Tile;
	}

	@Override
	public boolean update(int deltaMS) {
		// TODO Tile update
		return true;
	}

	@Override
	public boolean update(int deltaMS, Input input) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
