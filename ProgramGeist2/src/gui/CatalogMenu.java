package gui;

import org.newdawn.slick.*;

import codeBlock.*;

public class CatalogMenu {
	
	boolean visible = true;
	
	private static final Color background = new Color(129, 169, 247);
	
	private static final int WIDTH = 200;
	private static int height;
	private static final int SPACING = 10;
	private static final int NUM_IMAGES = 6;
	
	private static Image[] images;
	
	private static CodeBlock[] codeBlocks;
	
	public CatalogMenu() {
		images = loadImages();
		codeBlocks = loadBlocks();
	}
	
	public void update(GameContainer gc, int deltaMS) {
		
		Input input = gc.getInput();
		height = gc.getHeight();
		
		// check if any code blocks have been clicked.
		
	}
	
	public void render(GameContainer gc, Graphics g) {
		if(!isVisible()) {
			return;
		}
		
		// figure out position
		// position from top left corner
		int yPos = 40;
		int xPos = 50;
		
		// draw background
		g.setColor(background);
		g.fillRect(0, 0, WIDTH, height);
		
		// render text
		g.setColor(Color.white);
		g.drawString("Code Blocks", xPos, yPos);
		
		// draw code blocks
		int totalHeight = 0;
		for(int i = 0; i<NUM_IMAGES; i++) {
			g.drawImage(images[i], xPos, yPos+20+totalHeight+SPACING*(i+1));
			totalHeight += images[i].getHeight();
		}
		
	}
	
	private int getNumImages() {
		return NUM_IMAGES;
	}

	public Image[] loadImages() {
		Image[] out = new Image[6];
		
		try {
			out[0] = new Image("res/Code Blocks/The_shape_of_a_Boolean_block.png");
			out[1] = new Image("res/Code Blocks/The_shape_of_a_C_block.png");
			out[2] = new Image("res/Code Blocks/The_shape_of_a_Cap_block.png");
			out[3] = new Image("res/Code Blocks/The_shape_of_a_Hat_Block.png");
			out[4] = new Image("res/Code Blocks/The_shape_of_a_Reporter_Block.png");
			out[5] = new Image("res/Code Blocks/The_shape_of_a_Stack_Block.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	public CodeBlock[] loadBlocks() {
		CodeBlock[] out = new CodeBlock[6];
		
		
		return out;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}
