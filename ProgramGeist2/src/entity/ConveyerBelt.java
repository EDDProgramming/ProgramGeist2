package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import world.EntityWorld;

public class ConveyerBelt extends Entity {
	
	int speed;
	boolean on;
	
	public ConveyerBelt(float x, float y, EntityWorld world) throws SlickException {
		super(x, y, world);
		
		hitbox = makeRectangle(x + 100, y, 200, 10);
		currentImage = new Image("res/Conveyer.png");
		Image[] images = new Image[]{new Image("res/Conveyer.png"), new Image("res/Conveyer2.png")};
		currentAnimation = new Animation(images, 1);
		speed = 4;
		on = false;
		entityType = EntityType.GamePiece;
	}
	
	public void CollideAction(PhysicsObject other) {
		if(on == true) {
			other.applyForce(speed / 4, 0);
		}
	}
	
	private void ToggleOn() {
		on = !on;
		animated = on;
	}
	
	private void SetSpeed(int amount) {
		speed = amount;
		currentAnimation.setSpeed(speed);
	}
	
	@Override
	public boolean update(int deltaMS, Input input) {
		
		if(input.isKeyPressed(Input.KEY_LSHIFT)) {
			ToggleOn();
		}
		
		if(input.isKeyDown(Input.KEY_A)) {
			SetSpeed(speed - 1);
		}
		
		if(input.isKeyDown(Input.KEY_D)) {
			SetSpeed(speed + 1);
		}
		
		return true;
	}

}
