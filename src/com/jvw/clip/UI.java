package com.jvw.clip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joris on 19-4-14.
 */
public class UI implements ActionListener {
	private JButton start_stop_button;
	private JTextArea log_text_area;
	private JPanel contentPane;
	private ClipServer server;

	public UI() {
		Log.init(log_text_area);
		this.server = new ClipServer();
		JFrame frame = new JFrame("Clip");
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		start_stop_button.addActionListener(this);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (server.isRunning()) {
			server.stop();
			start_stop_button.setText("Start");
		} else {
			server.start();
			start_stop_button.setText("Stop");
		}
	}
}
