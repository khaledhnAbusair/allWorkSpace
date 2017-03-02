package suncertify.main;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import suncertify.db.Engine;
import suncertify.db.EngineService;
import suncertify.db.EngineServiceException;
import suncertify.db.EngineServiceLoader;
import suncertify.db.Session;
import suncertify.model.db.DBFileContractorModel;
import suncertify.pref.UserPref;
import suncertify.ui.UIUtil;
import suncertify.ui.client.ClientMainFrame;
import suncertify.ui.client.ConnectionDialog;
import suncertify.ui.client.ConnectionDialog.Option;

/**
 * A main client application launcher.
 * <p>
 * 
 * Its main method {@link #main(java.lang.String[]) } will launches client
 * application in alone or networked client mode according to mode flag passed
 * to it.
 * 
 * According to mode flag passed to its <code>main</code> method, a dialog will
 * prompt the user to select the database file or the network service to connect
 * to, If the user cancels the selection the application will be exited, if
 * confirms selection, an {@link Engine} instance will be created and the client
 * application will be loaded.
 * 
 * Entries for connection dialog will be initially filled with configuration
 * previously used by user and persisted in preferences storage.
 * {@link UserPref} is used to load the configuration. The entries are persisted
 * back to storage only if the users confirms the entry and an {@link Engine}
 * was successfully created and connected to a database local file or a network
 * service.
 * 
 * @author Mohammad S. Abdellatif
 * @see ServerMain
 * @see ClientMainFrame
 * @see EngineService
 * @see Engine
 */
public class ClientMain {

	/**
	 * Main method for loading client application according to mode flag passed
	 * through <code>args</code>.
	 * <p>
	 * 
	 * If <code>args.length()</code> is <code>0</code>, the mode will be
	 * considered as network client mode, else, index <code>0</code> element
	 * will be validated if it is equals to "alone" or "" model flags, if is,
	 * the client application will be loaded, else an
	 * <code>IllegalArgumentException</code> will be thrown.
	 * 
	 * Entries for connection dialog will be initially filled with configuration
	 * previously used by user and persisted in preferences storage.
	 * 
	 * {@link UserPref} is used to load the configuration. The entries are
	 * persisted back to storage only if the users confirms the entry and an
	 * {@link Engine} was successfully created and connected to a database local
	 * file or a network service.
	 * 
	 * If creation for an {@link Engine} instance is failed according to client
	 * entries, it will be notified about the cause of failure and prompted to
	 * fill the entries again.
	 * 
	 * The application will be exited in case the client cancels the selection
	 * for a database or closes the application main frame if he was already
	 * connected to a database.
	 * 
	 * @param args
	 *            client application arguments.
	 * @throws IllegalArgumentException
	 *             if passed mode flag is not what expected
	 */
	public static void main(String[] args) throws IllegalArgumentException {
		String mode =
				(args.length == 0 ? Main.CLIENT_MODE : args[0]).trim()
						.toLowerCase();
		ClientMainFrameInvoker clientFrameInvoker =
				new ClientMainFrameInvoker();
		ConnectionDialog.ConnectionType connectionType;

		if (Main.ALONE_MODE.equals(mode)) {
			connectionType = ConnectionDialog.ConnectionType.LOCAL;
		} else if (Main.CLIENT_MODE.equals(mode)) {
			connectionType = ConnectionDialog.ConnectionType.REMOTE;
		} else {
			throw new IllegalArgumentException(
					"Unknown mode flag, expected empty or " + Main.CLIENT_MODE);
		}
		clientFrameInvoker.connectionType = connectionType;

		EventQueue.invokeLater(clientFrameInvoker);
	}

	/**
	 * A thread to launch client application.
	 */
	private static class ClientMainFrameInvoker implements Runnable {

		ConnectionDialog.ConnectionType connectionType;

		/**
		 * Prompt user to select a database according to {@link #connectionType}
		 * then run client application.
		 */
		@Override
		public void run() {
			ClientMainFrame mainFrame = new ClientMainFrame();
			ConnectionDialog dialog =
					new ConnectionDialog(mainFrame, connectionType, true);
			UserPref clientPref = new UserPref();

			if (connectionType == ConnectionDialog.ConnectionType.LOCAL) {
				dialog.setSelectedLocalFile(clientPref.getDBFile());
			} else {// remote
				dialog.setHost(clientPref.getHost());
				dialog.setServiceName(clientPref.getServiceName());
				dialog.setPort(clientPref.getPort());
			}

			mainFrame.setVisible(true);
			dialog.setLocationRelativeTo(mainFrame);
			try {
				while (true) {
					Option option = dialog.showConnectionDialog();
					Exception failure;

					if (option == Option.CANCEL) {
						mainFrame.setVisible(false);
						mainFrame.dispose();
						return;
					}
					try {
						EngineService service =
								EngineServiceLoader.locateEngineService();
						final DBFileContractorModel contractorModel;
						final Engine engine;
						Session session;
						StringBuilder connectionStringTitle =
								new StringBuilder("Database: ");

						switch (connectionType) {
						case LOCAL:
							File selectedLocalFile =
									dialog.getSelectedLocalFile();

							engine = service.getEngine(selectedLocalFile);
							clientPref.setDBFile(selectedLocalFile);
							connectionStringTitle.append(selectedLocalFile
									.getCanonicalPath());
							break;
						default:
							String host = dialog.getHost();
							Integer port = dialog.getPort();
							String serviceName = dialog.getServiceName();

							// REMOTE
							engine = service.getEngine(host, port, serviceName);
							clientPref.setHost(host);
							clientPref.setServiceName(serviceName);
							clientPref.setPort(port);
							connectionStringTitle.append("//").append(host)
									.append(":").append(port).append("/")
									.append(serviceName);
						}
						// persist preferences if creation for engine is
						// successful
						clientPref.flush();

						session = engine.newSession();
						contractorModel = new DBFileContractorModel(session);

						mainFrame.addWindowListener(new WindowAdapter() {

							@Override
							public void windowClosed(WindowEvent e) {
								try {
									contractorModel.discard();
								} finally {
									// always shutdown regardless of session
									if (connectionType == ConnectionDialog.ConnectionType.LOCAL) {
										engine.shutdown();
									}
								}
							}
						});
						mainFrame.setContractorModel(contractorModel);
						mainFrame.setTitle(mainFrame.getTitle() + "-"
								+ connectionStringTitle.toString());
						break;
					} catch (BackingStoreException ex) {
						failure = ex;
					} catch (EngineServiceException ex) {
						failure = ex;
					} catch (IllegalArgumentException ex) {
						// to catch if passed database file is not a database
						// file
						failure = ex;
					}
					JOptionPane.showMessageDialog(
							mainFrame,
							"Failed while connectings to database: "
									+ failure.getMessage(),
							"Connection failure", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE,
						e.getMessage(), e);
				UIUtil.reportInternalFailure(e);
				mainFrame.dispose();
			}
		}
	}
}
