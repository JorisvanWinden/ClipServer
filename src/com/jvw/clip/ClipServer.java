package com.jvw.clip;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Joris on 14-4-14.
 */
public class ClipServer implements ClipboardOwner, Runnable {

	private ServerSocket server;
	private boolean running = false;

	public void start() {
		new Thread(this).start();
	}

	public void stop() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setClipboard(String msg) {
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection toClip = new StringSelection(msg);
		clipBoard.setContents(toClip, this);
		Log.log(msg + " copied to clipboard");
	}


	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

	@Override
	public void run() {
		running = true;
		try {
			server = new ServerSocket(60607);
			Log.log("Your ip address is: " + InetAddress.getLocalHost().getHostAddress());
			Log.log("Your port is: " + server.getLocalPort());
			while (true) {
				Socket socket = server.accept();
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeBoolean(true);
				out.flush();
				String msg = in.readUTF();
				new ClipServer().setClipboard(msg);
				in.close();
				out.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		running = false;
	}

	public boolean isRunning() {
		return running;
	}
}
