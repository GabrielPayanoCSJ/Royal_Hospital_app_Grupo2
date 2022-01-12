/**
 * 
 */

// PACKAGE 
package hospital.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// IMPORTS
import javax.swing.JOptionPane;

import hospital.mail.server.controller.Utils_Methods;

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

	public static String inputGUIpane(String quest) {
		return JOptionPane.showInputDialog(quest);
	}

	public static String inputGUIpane(String quest, String title, String oldName) {
		return (String) JOptionPane.showInputDialog(null, quest, title, JOptionPane.QUESTION_MESSAGE, null, null,
				oldName);
	}

	public static String inputGUIpane(String quest, String title) {
		return JOptionPane.showInputDialog(null, quest, title, JOptionPane.QUESTION_MESSAGE);
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

	public static boolean checkEmail(String email) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \r\n"
				+ "+ \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

		if (Pattern.matches(regexPattern, (CharSequence) email)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkEmailFormat(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static String getPass(char[] pass) {

		String strpass = "";
		if (pass.length != 0) {
			for (int i = 0; i < pass.length; i++) {
				strpass += pass[i];
			}
		} else {
			strpass = null;
		}

		return strpass;
	}
}
