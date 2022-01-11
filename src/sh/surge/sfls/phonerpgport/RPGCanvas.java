package sh.surge.sfls.phonerpgport;

import java.io.IOException;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class RPGCanvas extends GameCanvas implements Runnable {
	boolean upKey = false;
	boolean downKey = false;
	boolean rightKey = false;
	boolean leftKey = false;
	int characterX = 5;
	int characterY = 5;
	Image tileset;
	Image mainCharacter;
	
	MapRenderer mapRenderer = new MapRenderer();
	
	public RPGCanvas() throws IOException {
		super(false);
		
		tileset = Image.createImage("/assets/tilesets/bedroom.png");
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
		if (upKey) characterY-=2;
		if (downKey) characterY+=2;
		if (leftKey) characterX-=2;
		if (rightKey) characterX+=2;
		characterX%=240;
		
		g.setColor(0x000042);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		mapRenderer.drawMap(g, tileset);
		g.drawImage(mainCharacter, characterX, characterY, 0);
	}
	
	public void run() {
		while(true) {
			repaint();
			try {Thread.sleep(1000/30);} catch (Exception ex) {}
		}
	}
}