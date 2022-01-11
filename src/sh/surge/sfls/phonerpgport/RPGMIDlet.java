package sh.surge.sfls.phonerpgport;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class RPGMIDlet extends MIDlet {
	public void startApp() {
		try {
			RPGCanvas canvas = new RPGCanvas();
			Display.getDisplay(this).setCurrent(canvas);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void pauseApp() {}
	
	public void destroyApp(boolean unconditional) {}
}