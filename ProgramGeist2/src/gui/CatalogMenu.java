package gui;

import java.awt.List;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import org.newdawn.slick.*;

import world.EntityWorld;
import codeBlock.*;

public class CatalogMenu {
	
	boolean visible = true;
	
	private static final Color background = new Color(129, 169, 247);
	
	private static final int WIDTH = 200;
	private static int height;
	private static final int SPACING = 10;
	private static final int NUM_BLOCKS = 6;
	
	private static Image[] images;
	
	private ArrayList<CodeBlock> codeBlocks;
	
	public EntityWorld world;
	
	public CatalogMenu(EntityWorld _world) {
		images = loadImages();
		world = _world;
		codeBlocks = loadBlocks();
		
	}
	
	public void update(GameContainer gc, int deltaMS) {
		
		Input input = gc.getInput();
		height = gc.getHeight();
		
		// check if any code blocks have been clicked.
		
		for(int i = 0; i<codeBlocks.size(); i++) {
			codeBlocks.get(i).update(deltaMS, input);
		}
		
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
		
//		// draw code blocks
//		int totalHeight = 0;
//		for(int i = 0; i<NUM_BLOCKS; i++) {
//			g.drawImage(images[i], xPos, yPos+20+totalHeight+SPACING*(i+1));
//			totalHeight += images[i].getHeight();
//		}
		
		for(int i = 0; i<codeBlocks.size(); i++) {
			codeBlocks.get(i).render(g, 0, 0);
		}
		
	}
	
	private int getNumImages() { return NUM_BLOCKS;	}

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
	
	public ArrayList<CodeBlock> loadBlocks() {
		ArrayList<CodeBlock> out = new ArrayList<CodeBlock>();

//		TODO make file loader
		try {
			float totalHeight = 0;

			//out.add(new printlnBlock(50, 100, world));
			//totalHeight += out.get(0).getHeight();
			
			CodeBlock add = new printlnBlock(50, 100, world);
			out.add(add);
			
			//world.addEntity(add);
			
//			out.add(new WhenLevelStarts(50, 100+totalHeight+SPACING, world));
//			totalHeight += out.get(1).getHeight();
			
			for(int i = 0; i<out.size(); i++) {
				out.get(i).setMenu();
			}
		}catch (SlickException e) {
			System.out.println("COULD NOT LOAD BLOCKS");
			e.printStackTrace();
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
