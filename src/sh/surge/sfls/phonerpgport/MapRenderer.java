package sh.surge.sfls.phonerpgport;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class MapRenderer {
	private static int TILE_SIZE = 16;
	
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
	
	public void drawMap(Graphics g, Image tileset, byte[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				drawTile(g, tileset, x, y, map[y][x]);
			}
		}
	}
	
	public void drawExcessTiles(Graphics g, Image tileset, byte[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				switch (map[y][x]) {
					case 3:
						drawTile(g, tileset, x, y + 1, map[y][x]);
					case 10:
					case 11:
					case 13:
						drawTile(g, tileset, x, y - 1, map[y][x]);
					default:
						break;
				}
			}
		}
	}
}