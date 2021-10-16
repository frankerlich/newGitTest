package at.coder.buchhaltung.utils;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static final int RECHNUNG_ANZAHL_DIGITS = 10;
	
	public static void closeWindow(Node o) {
		Stage s = (Stage) o.getScene().getWindow();
		s.close();
	}
	
	public static String getRechnungsnummerFromId(int id) {
		int digitCount = String.valueOf(id).length();
		int numberZersos = RECHNUNG_ANZAHL_DIGITS - digitCount;
		String s = "";
		for(int i = 0; i < numberZersos; i++) {
			s += "0";
		}
		s += String.valueOf(id);
		return s;
	}
}
