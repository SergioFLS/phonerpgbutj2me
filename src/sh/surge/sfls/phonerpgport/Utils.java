package sh.surge.sfls.phonerpgport;

public class Utils {
	public static boolean arrayOr(boolean[] values) {
		boolean output = false;
		for (int i = 0; i < values.length; i++){
			if (values[i]) {
				output = true;
				break;
			}
		}
		
		return output;
	}
}