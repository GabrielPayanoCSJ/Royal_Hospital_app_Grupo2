/**
 * 
 */

// PACKAGE 
package hospital.tools;

// IMPORTS
import javax.swing.JOptionPane;

/**
 * @author Guillermo González
 * @version 1.0
 */
public class Tool {

	public static void showGUIinfo(String info, String title) {
		JOptionPane.showMessageDialog(null, info, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showGUIerror(String error, String title) {
		loadOptionPane(error, title, JOptionPane.ERROR_MESSAGE);
	}

	private static void loadOptionPane(String error, String title, int option) {
		JOptionPane.showMessageDialog(null, error, title, option);
	}



	public static void showConsoleError(String error) {
		showConsoleMessage("\n *** ERROR *** " + error, true);
	}
	
	public static void showConsoleMessage(String message, boolean breakLine) {
		if (breakLine) {
			System.out.println(message);
		} else {
			System.out.print(message);
		}
	}
}
