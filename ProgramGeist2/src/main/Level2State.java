package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import world.TestWorld2;


public class Level2State extends BasicGameState {
	public static final int ID = 5;
	
	private TestWorld2 world;
	private final int updatesPerSecond = 60;
	private final int msPerUpdate = 1000 / updatesPerSecond;
	private Camera camera;
	
	public Level2State() {
	}
	
	void startGame(GameContainer gc) throws SlickException {
		camera = new Camera();
		world = new TestWorld2(camera);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {	
		gc.setMinimumLogicUpdateInterval(msPerUpdate);
		gc.setMaximumLogicUpdateInterval(msPerUpdate);
		System.out.println("Init GameState");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if(world != null) {
			world.render(gc, g, camera.getX(), camera.getY());
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
		super.enter(gc, game);
		startGame(gc);
		System.out.println("Enter GameState");
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int deltaMS) throws SlickException {
		if(world != null) {
			camera.update(deltaMS);
			world.update(gc, deltaMS);
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_F1)) {
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		// TODO add isGameOver code
	}
	
}
