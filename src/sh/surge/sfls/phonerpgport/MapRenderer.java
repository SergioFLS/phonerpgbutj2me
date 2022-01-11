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
		{9,11,9,9,4,4,4,4,4},
		{4,4,4,4,4,4,4,4,4}
	};
	
	private void drawTile(Graphics g, Image m, int x, int y, int tileIndex, int mirror) {
		g.drawRegion(
			m,
			tileIndex * TILE_SIZE, 0,
			TILE_SIZE, TILE_SIZE,
			mirror,
			x * TILE_SIZE, y * TILE_SIZE,
			0
		);
	}
	
	private void drawTile(Graphics g, Image m, int x, int y, int tileIndex) {
		drawTile(g, m, x, y, tileIndex, Sprite.TRANS_NONE);
	}
	
	public void drawMap(Graphics g, Image m) {
		for (int y = 0; y < tempMap.length; y++) {
			boolean insideTable = false;
			for (int x = 0; x < tempMap[y].length; x++) {
				if (tempMap[y][x] == 9)
					insideTable = true;
				else
					insideTable = false;
				
				boolean reachedBeginOfTable = false;
				boolean reachedEndOfTable = false;
				if (insideTable) {
					// begin of table
					try { 
						if (!(tempMap[y][x-1] == 9 ||
						tempMap[y][x-1] == 10 ||
						tempMap[y][x-1] == 11))
							reachedBeginOfTable = true;
					} catch (ArrayIndexOutOfBoundsException ex) { reachedBeginOfTable = true;}
					
					// end of table
					try { 
						if (!(tempMap[y][x+1] == 9 ||
						tempMap[y][x+1] == 10 ||
						tempMap[y][x+1] == 11))
							reachedEndOfTable = true;
					} catch (ArrayIndexOutOfBoundsException ex) { reachedEndOfTable = true;}
				}
				
				if (insideTable) {
					if (reachedBeginOfTable)
						drawTile(g, m, x, y, 9);
					else if (reachedEndOfTable)
						drawTile(g, m, x, y, 9, Sprite.TRANS_MIRROR);
					else
						drawTile(g, m, x, y, 10);
				} else {
					drawTile(g, m, x, y, tempMap[y][x]);
				}
			}
		}
	}
}