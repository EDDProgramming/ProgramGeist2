package entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

import world.EntityWorld;

public class ConveyerBelt extends Entity {
	
	int speed;
	int maxSpeed;
	boolean on;
	
	Animation backwardAnim;
	Animation forwardAnim;
	
	TextField speedMod;
	
	public ConveyerBelt(float x, float y, EntityWorld world, GUIContext gc) throws SlickException{
		super(x, y, world);
		
		hitbox = makeRectangle(x + 100, y, 200, 10);
		
		animated = true;
		currentImage = new Image("res/Conveyer.png");
		Image[] images = new Image[]{new Image("res/Conveyer.png"), new Image("res/Conveyer2.png"),
									 new Image("res/Conveyer3.png"), new Image("res/Conveyer4.png"),
									 new Image("res/Conveyer5.png"), new Image("res/Conveyer6.png"), };
		forwardAnim = new Animation(images, 200);
		Image[] backImages = new Image[images.length];
		for(int i = 0; i < images.length; i++) {
			backImages[i] = images[(images.length - 1) - i];
		}
		backwardAnim = new Animation(backImages, 200);
		currentAnimation = forwardAnim;
		currentAnimation.stop();
		
		speed = 4;
		//Note, max speed should be changed based on the animation speed since that is what causes the game to freeze.
		maxSpeed = 800;
		on = false;
		
		org.newdawn.slick.Font lucidaConsole = new UnicodeFont("res/Fonts/lucon.ttf", 5, false, false);
		speedMod = new TextField(gc, lucidaConsole, (int)position.x, (int)position.y -40, 100, 20);
		speedMod.setConsumeEvents(true);
		speedMod.setBackgroundColor(Color.gray);
		speedMod.setBorderColor(Color.black);
		speedMod.setText("25");
		world.addAbstract(speedMod);
		
		entityType = EntityType.GamePiece;
	}
	
	public void CollideAction(PhysicsObject other) {
		if(on == true) {
			other.applyForce(speed / 4, 0);
		}
	}
	
	private void ToggleOn(boolean on) {
		this.on = on;
		if(!on) {
			currentAnimation.stop();
		}
		else {
			currentAnimation.start();
		}
	}
	
	private void SetSpeed(int amount) {
		speed = amount;
		if(speed > maxSpeed) {
			speed = maxSpeed;
		}
		
		if(speed < -maxSpeed) {
			speed = -maxSpeed;
		}
		
		if(speed < 0) {
			currentAnimation = backwardAnim;
		}
		
		if(speed > 0) {
			currentAnimation = forwardAnim;
		}
		
		if(speed == 0) {
			ToggleOn(false);
		}
		
		currentAnimation.setSpeed((float) (Math.abs(speed) / 4));
		System.out.println("ConveyerSpeed:" +speed);
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		
		if(input.isKeyPressed(Input.KEY_LSHIFT)) {
			ToggleOn(!on);
		}
		
		if(input.isKeyDown(Input.KEY_A)) {
			SetSpeed(speed - 1);
		}
		
		if(input.isKeyDown(Input.KEY_D)) {
			SetSpeed(speed + 1);
		}
		
		if(speedMod.hasFocus()){
			speedMod.setBackgroundColor(Color.white);
		}
		
		else {
			speedMod.setBackgroundColor(Color.gray);
		}
		
		return true;
	}

}
