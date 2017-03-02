package suncertify.main;

import java.awt.EventQueue;

import suncertify.ui.server.ServerMainFrame;

/**
 * A server application launcher.
 * <p>
 * 
 * Its main method launches server application to allow the user to start a
 * network service for a database file.
 * 
 * @author Mohammad S. Abdellatif
 * @see Main
 */
public class ServerMain {

	/**
	 * Launches server application to allow the user to start a network service
	 * for a database file.
	 * 
	 * @param args
	 *            execution arguments, ignored.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ServerMainFrame mainFrame = new ServerMainFrame();
				mainFrame.setVisible(true);
			}
		});
	}
}
