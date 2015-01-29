package world;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import entity.*;
import main.Camera;

public class StartScreenWorld extends EntityWorld {
	
	public StartScreenWorld(Camera c) throws SlickException {
		super(c);
		// TODO Auto-generated constructor stub
	}

	private List<Entity> buttons = new ArrayList<Entity>();
	
	public void init(GameContainer gc) throws SlickException
	{
	}
}
