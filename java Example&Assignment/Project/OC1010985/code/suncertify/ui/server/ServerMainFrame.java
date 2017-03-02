package suncertify.ui.server;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import suncertify.db.EngineService;
import suncertify.db.EngineServiceException;
import suncertify.db.EngineServiceLoader;
import suncertify.db.server.NetworkService;
import suncertify.db.server.NetworkServiceEvent;
import suncertify.db.server.NetworkServiceEventListener;
import suncertify.db.server.NetworkServiceException;
import suncertify.pref.AdminPref;
import suncertify.ui.UIUtil;

/**
 * A frame which allows a user to configure, start, and monitor for a database
 * file network service, it can manage only one service at time.
 * <p>
 * 
 * It consist of three parts:
 * <ul>
 * <li>Network service configuration entries: capture user entries for
 * configuring network service which are database file path, service name, and
 * service port.</li>
 * <li>Network service statistics display: displays start and stop statistics
 * for network service.</li>
 * <li>Actions bar: contains actions for starting and stopping a network service
 * </li>
 * </ul>
 * User configuration entries are loaded and persisted using {@link AdminPref}
 * instance, persisting for those entries is only done for successfully started
 * network service by calling {@link AdminPref#flush()}.<br>
 * 
 * This frame uses
 * {@link EngineService#createNetworkService(java.io.File, java.lang.String, int)}
 * to create a network service for a database file. An implementation for engine
 * service is loaded through {@link EngineServiceLoader#locateEngineService()}
 * factory method.<br>
 * 
 * Once a network service is started successfully, configuration entries will be
 * disabled until the network service is stopped.
 * 
 * @author Mohammad S. Abdellatif
 * @see EngineServiceLoader
 * @see EngineService
 * @see NetworkService
 * @see AdminPref
 */
public class ServerMainFrame extends JFrame implements
		NetworkServiceEventListener {
	private NetworkServiceConfigPanel networkServiceInfoPanel;
	private NetworkServiceStatisticsPanel statisticsPanel;
	private NetworkService networkService;
	private StopAction stopAction = new StopAction();
	private StartServerAction startServerAction = new StartServerAction();
	private CloseAction closeAction = new CloseAction();
	private AdminPref adminPref = new AdminPref();

	/**
	 * Construct a new frame for configuring and managing a database network
	 * service.
	 * <p>
	 * 
	 * Configuration entries will be initially filled with values returned by a
	 * {@link AdminPref} instance.
	 */
	public ServerMainFrame() {
		setTitle("Server Administration");

		initComponents();

		pack();

		// it is controlled by me
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocation(UIUtil.getWindowCentralizedLocation(this));

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (confirmExit()) {
					dispose();
				}
			}
		});
	}

	/**
	 * Initialize frame GUI and actions.
	 */
	private void initComponents() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		initMenu();
		initNetworkServiceConfigPanel();
		initCommandsPanel();
		initStatisticsPanel();
	}

	/**
	 * Initialize menu.
	 */
	private void initMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu actionMenu = new JMenu("Action");
		JMenuItem exit = new JMenuItem(closeAction);

		// ALT + F4
		exit.setAccelerator(KeyStroke.getKeyStroke(115,
				InputEvent.ALT_DOWN_MASK));
		fileMenu.add(exit);

		bar.add(fileMenu);
		bar.add(actionMenu);

		fileMenu.setMnemonic('F');
		actionMenu.setMnemonic('A');

		actionMenu.add(startServerAction);
		actionMenu.add(stopAction);

		setJMenuBar(bar);
	}

	/**
	 * Initialize commands panel.
	 */
	private void initCommandsPanel() {
		JPanel commandsPanel;
		JButton closeButton;
		JButton shutdownButton;
		JButton startButton;

		commandsPanel = new JPanel();
		commandsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		startButton = new JButton(startServerAction);
		commandsPanel.add(startButton);

		shutdownButton = new JButton(stopAction);
		commandsPanel.add(shutdownButton);

		closeButton = new JButton(closeAction);
		commandsPanel.add(closeButton);

		add(commandsPanel);
	}

	/**
	 * Initialize network service configuration panel.
	 */
	private void initNetworkServiceConfigPanel() {
		JPanel databaseInfoPanel;

		networkServiceInfoPanel = new NetworkServiceConfigPanel();

		networkServiceInfoPanel.setSelectedFile(adminPref.getDBFileLocation());
		networkServiceInfoPanel.setPort(adminPref.getPort());
		networkServiceInfoPanel.setServiceName(adminPref.getServiceName());

		databaseInfoPanel = new JPanel();
		databaseInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		databaseInfoPanel.add(networkServiceInfoPanel);
		databaseInfoPanel.setBorder(new TitledBorder("Network Service Info."));

		add(databaseInfoPanel);
	}

	/**
	 * Captures network service starting event generated by observed network
	 * service.
	 * <p>
	 * When called, configuration entries will be disabled and persisted to
	 * preferences storage.
	 * 
	 * @param event
	 *            network service starting event.
	 */
	@Override
	public void serviceStarted(NetworkServiceEvent event) {
		File selectedFile = networkServiceInfoPanel.getSelectedFile();
		String serviceName = networkServiceInfoPanel.getServiceName();
		Integer port = networkServiceInfoPanel.getPort();

		stopAction.setEnabled(true);
		networkServiceInfoPanel.setEnabled(false);
		startServerAction.setEnabled(false);

		adminPref.setDBFileLocation(selectedFile);
		adminPref.setPort(port);
		adminPref.setServiceName(serviceName);

		try {
			adminPref.flush();
		} catch (BackingStoreException ex) {
			Logger.getLogger(ServerMainFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	/**
	 * Captures network service stop event generated by observed network
	 * service.
	 * <p>
	 * When called, configuration entries will be enabled again.
	 * 
	 * @param event
	 *            service stop event.
	 */
	@Override
	public void serviceStopped(NetworkServiceEvent event) {
		startServerAction.setEnabled(true);
		networkServiceInfoPanel.setEnabled(true);
		stopAction.setEnabled(false);
	}

	/**
	 * Initialize statistics display panel.
	 */
	private void initStatisticsPanel() {
		JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));

		statisticsPanel = new NetworkServiceStatisticsPanel();
		container.add(statisticsPanel);
		container.setBorder(new TitledBorder("Statistics"));
		add(container);
	}

	/**
	 * Confirm user if she wants to exist application or not.
	 * 
	 * @return <code>true</code> if user confirms, otherwise, <code>false</code>
	 *         .
	 */
	private boolean confirmExit() {
		if (networkService == null || !networkService.isRunning()) {
			return true;
		} else {
			// exist and running
			int selection =
					JOptionPane
							.showConfirmDialog(
									null,
									"Closing window will shutdown server, Are you sure",
									null, JOptionPane.OK_CANCEL_OPTION);

			if (selection == JOptionPane.OK_OPTION) {
				try {
					networkService.stop();
				} catch (NetworkServiceException ex) {
					Logger.getLogger(ServerMainFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * The action for validating network service configuration entries and
	 * starting it.
	 */
	private class StartServerAction extends AbstractAction {

		/**
		 * Construct a new start server action.
		 */
		public StartServerAction() {
			super("Start");
			putValue(Action.MNEMONIC_KEY, (int) 'S');
		}

		/**
		 * Validates user entries for network service configuration then start
		 * the network service if they are valid.
		 * 
		 * @param e
		 *            start server event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String errorMessage = networkServiceInfoPanel.getErrorMessage();
			if (errorMessage != null) {
				JOptionPane.showMessageDialog(null, errorMessage,
						"Invalid Entries", JOptionPane.ERROR_MESSAGE);
			} else {
				File dbLocation = networkServiceInfoPanel.getSelectedFile();
				String serviceName = networkServiceInfoPanel.getServiceName();
				int port = networkServiceInfoPanel.getPort();

				try {
					EngineService service =
							EngineServiceLoader.locateEngineService();

					networkService =
							service.createNetworkService(dbLocation,
									serviceName, port);
					networkService
							.addNetworkServiceEventListener(ServerMainFrame.this);
					networkService
							.addNetworkServiceEventListener(statisticsPanel);

					networkService.start();
				} catch (NetworkServiceException ex) {
					JOptionPane.showMessageDialog(null,
							"unable to start server:\n" + ex.getMessage());
					Logger.getLogger(ServerMainFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (EngineServiceException ex) {
					JOptionPane.showMessageDialog(null,
							"unable to start server:\n" + ex.getMessage());
					Logger.getLogger(ServerMainFrame.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	/**
	 * An action for stopping a network service currently running
	 */
	private class StopAction extends AbstractAction {

		/**
		 * Construct a new stop action.
		 */
		public StopAction() {
			super("Stop");
			putValue(Action.MNEMONIC_KEY, (int) 'O');
			setEnabled(false);// initially not enabled
		}

		/**
		 * When called, the network service will be stopped.
		 * 
		 * @param e
		 *            stop action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				networkService.stop();
			} catch (NetworkServiceException ex) {
				Logger.getLogger(ServerMainFrame.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		}
	}

	/**
	 * An action for closing server main frame.
	 */
	private class CloseAction extends AbstractAction {

		/**
		 * Construct a new close action.
		 */
		public CloseAction() {
			super("Close");
			putValue(Action.MNEMONIC_KEY, (int) 'C');
		}

		/**
		 * Close server main frame.
		 * 
		 * @param e
		 *            close action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (confirmExit()) {
				dispose();
			}
		}
	}
}
