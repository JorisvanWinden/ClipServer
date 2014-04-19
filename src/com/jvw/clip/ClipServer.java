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
public class ClipServer implements ClipboardOwner {

	public static void main(String[] args) {
		try {

			ServerSocket server = new ServerSocket(60607);
			System.out.println("Your ip address is: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Your port is: " + server.getLocalPort());
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
		}
	}

	public void start() {

	}

	public void setClipboard(String msg) {
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection toClip = new StringSelection(msg);
		clipBoard.setContents(toClip, this);
		System.out.println(msg + " copied to clipboard");
	}


	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {

	}
}
