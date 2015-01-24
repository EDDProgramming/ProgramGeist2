package gui;

import org.newdawn.slick.*;

public class CatalogMenu {
	
	boolean visible = true;
	
	private static final Color background = new Color(0, 0, 0);
	
	private static final int width = 200;
	private static int height;
	private static final int spacing = 50;
	private static final int numImages = 2;
	
	private static Image[] images;
	
	public CatalogMenu() {
		images = loadImages();
		
	}
	
	public void update(GameContainer gc, int deltaMS) {
		
		Input input = gc.getInput();
		height = gc.getHeight();
		
	}
	
	public void render(GameContainer gc, Graphics g) {
		if(!isVisible()) {
			return;
		}
		
		// figure out position
		// position from top left corner
		int yPos = 0;
		int xPos = 0;
		
		// draw background
		g.setColor(background);
		g.fillRect(0, 0, width, height);
		
		
		// render text
		g.setColor(Color.white);
		g.drawString("Code Blocks", 0, 0);
		
		// draw code blocks
		for(int i = 0; i<numImages; i++) {
			
		}
	}
	
	private int getNumImages() {
		return numImages;
	}

	public Image[] loadImages() {
		Image[] out = new Image[2];
		
		try {
		out[0] = new Image("");
		} catch(SlickException e) {
			
		}
		
		return out;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}
