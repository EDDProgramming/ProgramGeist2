package menuButton;

import java.net.URL;

import ProgramGeist.entity.*;
import ProgramGeist.world.*;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class MenuButton1 extends Entity{
	
	private SpriteSheet buttonSheet;
	private Animation[] buttonToggle = new Animation[2];

	};
	
	
	public MenuButton1(EntityWorld world, float x, float y) {
		super(x, y, world);
		
		buttonSheet = new SpriteSheet("res/menu1", 240, 100);
		Image[] menu1 = new Image[1];
		menu1[0] = buttonSheet.getSprite(1, 1);
		buttonToggle[0] = new Animation(menu1[], 200, true);
	}

	@Override
	public boolean update(int deltaMS) {
		return false;
	}

}
