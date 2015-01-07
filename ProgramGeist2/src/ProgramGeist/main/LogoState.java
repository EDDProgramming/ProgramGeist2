package ProgramGeist.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

public class LogoState extends BasicGameState {
	public static final int ID = 0;
	Image logoImage;
	Color colMult = new Color(Color.white);
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		logoImage = new Image("res/Logo.png");
		colMult.a = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		logoImage.setColor(0, colMult.r, colMult.b, colMult.g, colMult.a);
		logoImage.setColor(1, colMult.r, colMult.b, colMult.g, colMult.a);
		logoImage.setColor(2, colMult.r, colMult.b, colMult.g, colMult.a);
		logoImage.setColor(3, colMult.r, colMult.b, colMult.g, colMult.a);
		logoImage.draw(gc.getWidth() / 2 - logoImage.getWidth() / 2, gc.getHeight() / 2 - logoImage.getHeight() / 2);
		
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int deltaMS) throws SlickException {
		//game.enterState(StartScreenState.ID, new FadeOutTransition(), new FadeInTransition());
		
	}
	
}
