package com.jvw.clip;

import javax.swing.*;

/**
 * Created by Joris on 19-4-14.
 */
public class UI {
	private JButton start_stop_button;
	private JTextArea log_text_area;
	private JPanel contentPane;

	public static void main(String[] args) {
		JFrame frame = new JFrame("<class name>");
		frame.setContentPane(new UI().contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
