package world;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import entity.*;
import menuButton.*;

public class StartScreenWorld extends EntityWorld {
	
	private List<Entity> buttons = new ArrayList<Entity>();
	private MenuButton1 button1;
	
	public void init(GameContainer gc) throws SlickException
	{
		addEntity(button1 = new MenuButton1(this, gc.getWidth() / 2.0f + 150, gc.getHeight() / 2.0f));
	}
}
