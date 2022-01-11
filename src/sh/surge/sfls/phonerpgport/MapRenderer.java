package sh.surge.sfls.phonerpgport;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class MapRenderer {
	private static int TILE_SIZE = 16;
	private static int[] TILES_COUCH = {5,6};
	private static int[] TILES_TABLE = {9,10,11};
	private byte[][] tempMap = {
		{0,0,3,0,2,0,3,0,0},
		{1,1,1,1,1,1,1,1,1},
		{4,4,4,4,4,4,7,8,7},
		{4,4,4,4,4,4,4,4,4},
		{5,5,4,5,5,5,5,5,5},
		{9,11,9,9,9,9,4,9,9},
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
	
	private boolean[] checkHorizontalBorders(boolean inside, int x, int y, int[] tileValues) {
		boolean[] beginEnd = new boolean[2];
		boolean[] checkValues = new boolean[tileValues.length];
		
		if (inside) {
			// begin of table
			try { 
				for (int i = 0; i < checkValues.length; i++) {
					checkValues[i] = tempMap[y][x-1] == tileValues[i];
				}
				if (!Utils.arrayOr(checkValues))
					beginEnd[0] = true;
			} catch (ArrayIndexOutOfBoundsException ex) { beginEnd[0] = true;}
			
			// end of table
			try {
				for (int i = 0; i < checkValues.length; i++) {
					checkValues[i] = tempMap[y][x+1] == tileValues[i];
				}
				if (!Utils.arrayOr(checkValues))
					beginEnd[1] = true;
			} catch (ArrayIndexOutOfBoundsException ex) { beginEnd[1] = true;}
		}
		
		return beginEnd;
	}
	
	public void drawMap(Graphics g, Image m) {
		for (int y = 0; y < tempMap.length; y++) {
			boolean insideTable = false;
			boolean insideCouch = false;
			for (int x = 0; x < tempMap[y].length; x++) {
				if (tempMap[y][x] == 9)
					insideTable = true;
				else
					insideTable = false;
				
				if (tempMap[y][x] == 5)
					insideCouch = true;
				else
					insideCouch = false;
				
				boolean[] beginEndTable = checkHorizontalBorders(insideTable, x, y, TILES_TABLE);
				boolean reachedBeginOfTable = beginEndTable[0];
				boolean reachedEndOfTable = beginEndTable[1];
				
				boolean[] beginEndCouch = checkHorizontalBorders(insideCouch, x, y, TILES_COUCH);
				boolean reachedBeginOfCouch = beginEndCouch[0];
				boolean reachedEndOfCouch = beginEndCouch[1];
				
				if (insideTable) {
					if (reachedBeginOfTable)
						drawTile(g, m, x, y, 9);
					else if (reachedEndOfTable)
						drawTile(g, m, x, y, 9, Sprite.TRANS_MIRROR);
					else
						drawTile(g, m, x, y, 10);
				} else if (insideCouch) {
					if (reachedBeginOfCouch)
						drawTile(g, m, x, y, 5);
					else if (reachedEndOfCouch)
						drawTile(g, m, x, y, 5, Sprite.TRANS_MIRROR);
					else
						drawTile(g, m, x, y, 6);
				} else {
					drawTile(g, m, x, y, tempMap[y][x]);
				}
			}
		}
	}
}