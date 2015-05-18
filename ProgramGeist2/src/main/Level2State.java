package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.render.batch.BatchRenderDevice;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;
import de.lessvoid.nifty.slick2d.input.SlickSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.render.batch.SlickBatchRenderBackendFactory;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.spi.input.InputSystem;
import de.lessvoid.nifty.spi.render.RenderDevice;
import de.lessvoid.nifty.spi.sound.SoundDevice;
import de.lessvoid.nifty.spi.time.TimeProvider;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import world.TestWorld2;


public class Level2State extends NiftyBasicGameState {
	public static final int ID = 5;
	
	private TestWorld2 world;
	private final int updatesPerSecond = 60;
	private final int msPerUpdate = 1000 / updatesPerSecond;
	private Camera camera;

	private Nifty nifty;
	
	public Level2State() {
	}
	
	void startGame(GameContainer gc) throws SlickException {
		camera = new Camera();
		world = new TestWorld2(camera, nifty);
		nifty = new Nifty(new BatchRenderDevice(SlickBatchRenderBackendFactory.create()), new SlickSoundDevice(), new SlickSlickInputSystem(this), new AccurateTimeProvider());
	}
	
//	@Override
//	public void init(GameContainer gc, StateBasedGame game) throws SlickException {	
//		gc.setMinimumLogicUpdateInterval(msPerUpdate);
//		gc.setMaximumLogicUpdateInterval(msPerUpdate);
//		System.out.println("Init GameState");
//	}

	@Override
	public void renderGame(GameContainer gc, StateBasedGame game, Graphics g) {
		if(world != null) {
			try {
				world.render(gc, g, camera.getX(), camera.getY());
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void enterState(GameContainer gc, StateBasedGame game) {
		try {
			startGame(gc);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter GameState");
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void updateGame(GameContainer gc, StateBasedGame game, int deltaMS) {
		if(world != null) {
			camera.update(deltaMS);
			world.update(gc, deltaMS);
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_F1)) {
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		// TODO add isGameOver code
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}
	
}
