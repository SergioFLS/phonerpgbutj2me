package sh.surge.sfls.phonerpgport;

import java.io.IOException;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class RPGCanvas extends GameCanvas implements Runnable {
	private byte[][] tempMap = {
		{0,0,3,0,2,0,3,0,0},
		{1,1,1,1,1,1,1,1,1},
		{5,4,4,4,4,4,10,11,10},
		{6,4,4,4,4,4,4,4,4},
		{4,4,4,4,4,4,7,8,9},
		{12,13,14,4,4,4,4,4,4},
		{4,4,4,4,4,4,4,4,4}
	};
	
	boolean upKey = false;
	boolean downKey = false;
	boolean rightKey = false;
	boolean leftKey = false;
	int characterX = 5;
	int characterY = 5;
	Image tileset;
	Image tilesetExcess;
	Image mainCharacter;
	
	MapRenderer mapRenderer = new MapRenderer();
	
	public RPGCanvas() throws IOException {
		super(false);
		
		setFullScreenMode(true);
		tileset = Image.createImage("/assets/tilesets/bedroom.png");
		tilesetExcess = Image.createImage("/assets/tilesets/bedroom_excess.png");
		mainCharacter = Image.createImage("/assets/mainchar.png");
		
		new Thread(this).start();
	}
	
	protected void keyPressed(int keyCode) {
		if (getGameAction(keyCode) == Canvas.UP) upKey = true;
		if (getGameAction(keyCode) == Canvas.DOWN) downKey = true;
		if (getGameAction(keyCode) == Canvas.RIGHT) rightKey = true;
		if (getGameAction(keyCode) == Canvas.LEFT) leftKey = true;
	}
	
	protected void keyReleased(int keyCode) {
		if (getGameAction(keyCode) == Canvas.UP) upKey = false;
		if (getGameAction(keyCode) == Canvas.DOWN) downKey = false;
		if (getGameAction(keyCode) == Canvas.RIGHT) rightKey = false;
		if (getGameAction(keyCode) == Canvas.LEFT) leftKey = false;
	}
	
	public void paint(Graphics g) {
		if (upKey) characterY--;
		if (downKey) characterY++;
		if (leftKey) characterX--;
		if (rightKey) characterX++;
		
		g.setColor(0x000042);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		mapRenderer.drawMap(g, tileset, tempMap);
		g.drawImage(mainCharacter, characterX, characterY, 0);
		mapRenderer.drawExcessTiles(g, tilesetExcess, tempMap);
		
	}
	
	public void run() {
		while(true) {
			repaint();
			try {Thread.sleep(1000/30);} catch (Exception ex) {}
		}
	}
}