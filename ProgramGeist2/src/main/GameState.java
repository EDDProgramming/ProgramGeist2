package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

import world.EntityWorld;

public class GameState extends BasicGameState {
	public static final int ID = 2;
	private EntityWorld entityWorld;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		
		entityWorld = new EntityWorld();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if(entityWorld != null)
		{
			entityWorld.render(0.0, 0.0);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int deltaMS) throws SlickException {
		if(entityWorld != null)
		{
			entityWorld.update(deltaMS);
		}
		
	}
	
}
