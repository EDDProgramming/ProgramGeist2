package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import world.EntityWorld;
import world.TestWorld;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.controls.dynamic.ScreenCreator;
import de.lessvoid.nifty.examples.test.TestScreen;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.input.*;


public class GameState extends BasicGameState {
	public static final int ID = 2;
	
	private EntityWorld world;
	private final int updatesPerSecond = 60;
	private final int msPerUpdate = 1000 / updatesPerSecond;
	private Camera camera;
	private Nifty nifty;
	private ScreenCreator screenCreator;
	private Screen screen;
	private ScreenController screenController;
	
	private LayerBuilder uiLayer;
	
	public GameState() {
	}
	
	void startGame(GameContainer gc) throws SlickException{
		camera = new Camera();
		nifty = new Nifty(new SlickRenderDevice(gc), new SlickSoundDevice(), new SlickSlickInputSystem(this), new AccurateTimeProvider());
		screenController = new TestScreen();
		screen = screenCreator.create(nifty);
		System.out.println(screen.getScreenId());
		
		world = new TestWorld(camera, gc, nifty);
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
			world.update(gc, deltaMS, nifty);
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_F2)) {
			game.enterState(5, new FadeOutTransition(), new FadeInTransition());
		}
		// TODO add isGameOver code
	}
	
}
