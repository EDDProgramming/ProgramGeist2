package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.render.ScalingRenderDevice;
import de.lessvoid.nifty.spi.input.InputSystem;
import de.lessvoid.nifty.spi.render.RenderDevice;
import de.lessvoid.nifty.spi.sound.SoundDevice;
import de.lessvoid.nifty.spi.time.TimeProvider;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import world.EntityWorld;
import world.TestWorld;


public class GameState extends BasicGameState {
	public static final int ID = 2;
	
	private EntityWorld world;
	private final int updatesPerSecond = 60;
	private final int msPerUpdate = 1000 / updatesPerSecond;
	private Camera camera;
	private Nifty nifty;
	
	public GameState() {
	}
	
	void startGame(GameContainer gc) throws SlickException{
		camera = new Camera();
		world = new TestWorld(camera, gc);
		//nifty = new Nifty(new , new NullSoundDevice, input, new AccurateTimeProvider);
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
