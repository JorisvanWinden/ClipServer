package com.jvw.clip;

import javax.swing.*;

/**
 * Created by Joris on 19-4-14.
 */
public class Log {
	private static JTextArea area;

	public static void init(JTextArea area) {
		Log.area = area;
	}

	public static void log(String msg) {
		area.append(msg + System.lineSeparator());
	}
}
