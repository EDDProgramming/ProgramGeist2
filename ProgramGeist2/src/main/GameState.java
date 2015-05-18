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
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.examples.test.TestScreen;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;
import de.lessvoid.nifty.render.batch.BatchRenderDevice;
import de.lessvoid.nifty.slick2d.render.batch.SlickBatchRenderBackendFactory;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.input.*;
import de.lessvoid.nifty.slick2d.NiftyStateBasedGame;


public class GameState extends NiftyBasicGameState {
	public static final int ID = 2;
	
	private EntityWorld world;
	private final int updatesPerSecond = 60;
	private final int msPerUpdate = 1000 / updatesPerSecond;
	private Camera camera;
	private Screen screen;
	
	public GameState() {
	}
	
	void startGame(GameContainer gc) throws SlickException {
		camera = new Camera();
		world = new TestWorld(camera, gc, getNifty());
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
		
		if(gc.getInput().isKeyPressed(Input.KEY_F2)) {
			game.enterState(5, new FadeOutTransition(), new FadeInTransition());
		}
		// TODO add isGameOver code
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame game) {
		nifty = new Nifty(new BatchRenderDevice(SlickBatchRenderBackendFactory.create()), new SlickSoundDevice(), new SlickSlickInputSystem(this), new AccurateTimeProvider());
		screen = new ScreenBuilder("default") {{
			controller(new DefaultScreenController());
			layer(new LayerBuilder("UI Layer") {{
				childLayoutCenter();
				panel(new PanelBuilder() {{
					width("50%");
					height("50%");
				}});
			}});
		}}.build(nifty);
	}
	
}
