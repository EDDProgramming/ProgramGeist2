package ProgramGeist.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

public class LogoState extends BasicGameState {
	public static final int ID = 0;
	Image logoImage;
	Color colMult = new Color(Color.white);
	final long logoDuration = 1000;
	long startTime;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		logoImage = new Image("res/logo.png");
		startTime = gc.getTime();
		colMult.a = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		logoImage.draw(gc.getWidth() / 2 - logoImage.getWidth() / 2, gc.getHeight() / 2 - logoImage.getHeight() / 2);
		
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int deltaMS) throws SlickException {
		if(startTime + logoDuration < gc.getTime())
		{
			game.enterState(StartScreenState.ID, new FadeOutTransition(), new FadeInTransition());
		}
		
	}
	
}
