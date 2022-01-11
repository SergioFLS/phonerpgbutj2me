package sh.surge.sfls.phonerpgport;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class MapRenderer {
	private static int TILE_SIZE = 16;
	private byte[][] tempMap = {
		{0,0,3,0,2,0,3,0,0},
		{1,1,1,1,1,1,1,1,1},
		{4,4,4,4,4,4,7,8,7},
		{4,4,4,4,4,4,4,4,4},
		{4,4,4,4,4,4,5,6,5},
		{9,10,9,11,9,4,4,4,4},
		{4,4,4,4,4,4,4,4,4}
	};
	
	private void drawTile(Graphics g, Image m, int x, int y, int tileIndex) {
		g.drawRegion(
			m,
			tileIndex * TILE_SIZE, 0,
			TILE_SIZE, TILE_SIZE,
			Sprite.TRANS_NONE,
			x * TILE_SIZE, y * TILE_SIZE,
			0
		);
	}
	
	public void drawMap(Graphics g, Image m) {
		for (int y = 0; y < tempMap.length; y++) {
			for (int x = 0; x < tempMap[y].length; x++) {
				drawTile(g, m, x, y, tempMap[y][x]);
				if (tempMap[y][x] == 9)
					System.out.println("touched left side of table");
			}
		}
	}
}