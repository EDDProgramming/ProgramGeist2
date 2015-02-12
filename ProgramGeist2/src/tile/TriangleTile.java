package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import entity.Entity;
import world.EntityWorld;

public class TriangleTile extends Entity {

	static Circle radius = new Circle(0, 0, 50);
	
	public TriangleTile(int x, int y, EntityWorld world) throws SlickException {
		super(x, y, makeTriangle(x, y, -50, 50, 50, 50, 50, -50), radius, false, world);
		
		image = new Image("res/TriangleTile.png");
		
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
