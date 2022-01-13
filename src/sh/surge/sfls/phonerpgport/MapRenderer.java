package sh.surge.sfls.phonerpgport;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class MapRenderer {
	private static int TILE_SIZE = 16;
	private byte[][] tempMap = {
		{0,0,3,0,2,0,3,0,0},
		{1,1,1,1,1,1,1,1,1},
		{5,4,4,4,4,4,10,11,10},
		{6,4,4,4,4,4,4,4,4},
		{4,4,4,4,4,4,7,8,9},
		{12,13,14,4,4,4,4,4,4},
		{4,4,4,4,4,4,4,4,4}
	};
	
	private void drawTile(Graphics g, Image tileset, int x, int y, int tileIndex, int mirror) {
		g.drawRegion(
			tileset,
			tileIndex * TILE_SIZE, 0,
			TILE_SIZE, TILE_SIZE,
			mirror,
			x * TILE_SIZE, y * TILE_SIZE,
			0
		);
	}
	
	private void drawTile(Graphics g, Image tileset, int x, int y, int tileIndex) {
		drawTile(g, tileset, x, y, tileIndex, Sprite.TRANS_NONE);
	}
	
	public void drawMap(Graphics g, Image tileset) {
		for (int y = 0; y < tempMap.length; y++) {
			for (int x = 0; x < tempMap[y].length; x++) {
				drawTile(g, tileset, x, y, tempMap[y][x]);
			}
		}
	}
}