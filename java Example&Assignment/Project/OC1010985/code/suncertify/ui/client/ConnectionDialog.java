package suncertify.ui.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import suncertify.ui.FileSelectionComponent;

/**
 * A dialog to prompt the selection for a database file resides in file system
 * or connecting to a database network service.
 * <p>
 * 
 * Confirmation for selecting a local/remote database can be verified by calling
 * {@link #getOption() }, if returned value is equals to {@link Option#CONNECT}.
 * <br>
 * The entries in an instance of this dialog depends on connection type passed
 * through its constructor:
 * <ul>
 * <li>
 * If a {@link ConnectionType#LOCAL} was chosen the dialog will display a
 * {@link FileSelectionComponent} to allow selection for a database file resides
 * in file system, the file can be retrieved after confirms by calling
 * {@link #getSelectedLocalFile()}.</li>
 * <li>
 * If a {@link ConnectionType#REMOTE} was chosen the dialog will display three
 * text fields, host name, service name, and service port. Entries for those
 * fields can be got by calling {@link #getHost()}, {@link #getServiceName() },
 * and {@link #getPort()}.</li>
 * </ul>
 * All getter methods will returns <code>null</code> in case user option is
 * {@link Option#CANCEL}.
 * 
 * Utility method {@link #showConnectionDialog()} will show the dialog and
 * returns the user selected option.
 * 
 * If prompted user clicks on connect button, {@link #getOption()} returns
 * {@link Option#CONNECT}, the dialog will be hide in case the entries are valid
 * , if not, a warning red message will be displayed to notify the user regard
 * missing or defected entries and it will remain visible.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ConnectionDialog extends JDialog {

	private FileSelectionComponent localDBPath;
	private RemoteConnectionInfoPanel remoteConnectionInfoPanel;
	private JTextField errorMessageDisplay;
	private ConnectionType connectionType;
	private Option option = Option.CANCEL;
	private String errorMessage;
	private Validator validator = new LocalConnectionValidator();
	private JButton connectButton;

	/**
	 * Defines the connection types the user can be prompted to fill its
	 * entries.
	 */
	public static enum ConnectionType {

		/**
		 * Defines the connection type as a remote connection to a database
		 * network service.
		 * <p>
		 * 
		 * The entries for this type is service host, service name, and service
		 * port.
		 */
		REMOTE,
		/**
		 * Defines the connection type as a local connection to a database file
		 * resides in file system.
		 * <p>
		 * 
		 * The entries for this type is file path.
		 */
		LOCAL;
	}

	/**
	 * Defines the option chosen by user before closing the a visible
	 * {@link ConnectionDialog}.
	 */
	public static enum Option {

		/**
		 * Defines connect option which validates user entries and confirms a
		 * connection to be established to a local/remote database.
		 */
		CONNECT,
		/**
		 * Defines the cancel option which cancels user entries to connect to a
		 * local/remote database.
		 */
		CANCEL;
	}

	/**
	 * Construct a new dialog with its parent frame is <code>parent</code> and
	 * display the entries according to connection type
	 * <code>connectionType</code>.
	 * <p>
	 * 
	 * If passed <code>modal</code> is <code>true</code>, parent frame
	 * <code>parent</code> will be blocked until this dialog is closed,
	 * otherwise, no blocking is performed.
	 * 
	 * @param parent
	 *            parent frame from which this dialog is displayed.
	 * @param connectionType
	 *            the dialog entries are displayed according to this passed
	 *            type.
	 * @param modal
	 *            if <code>true</code>, dialog will blocks it`s parent frame,
	 *            otherwise, no blocking is performed.
	 */
	public ConnectionDialog(java.awt.Frame parent,
			ConnectionType connectionType, boolean modal) {
		super(parent, modal);

		this.connectionType = connectionType;
		remoteConnectionInfoPanel = new RemoteConnectionInfoPanel();
		localDBPath = new FileSelectionComponent();

		validator =
				connectionType == ConnectionType.REMOTE
						? new RemoteConnectionValidator()
						: new LocalConnectionValidator();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Connection Dialog");

		initComponents();
		pack();

		setMinimumSize(getSize());
	}

	/**
	 * Gets the connection type for this dialog.
	 * 
	 * @return connection type for this dialog.
	 */
	public ConnectionType getConnectionType() {
		return connectionType;
	}

	/**
	 * Gets the option selected by prompted user.
	 * 
	 * @return the option selected by prompted user.
	 */
	public Option getOption() {
		return option;
	}

	/**
	 * Show the dialog with entries according to connection type passed to this
	 * instance constructor then returns the option selected by the prompted
	 * user when this dialog is closed.
	 * 
	 * @return option selected by prompted user when this dialog is closed.
	 */
	public Option showConnectionDialog() {
		errorMessageDisplay.setText("");
		getRootPane().setDefaultButton(connectButton);
		option = Option.CANCEL;
		setVisible(true);
		return getOption();
	}

	/**
	 * Returns the local database file selected by user.
	 * <p>
	 * If {@link #getConnectionType()} is equals to
	 * {@link ConnectionType#REMOTE} it returns <code>null</code>, otherwise the
	 * entry is returned.
	 * 
	 * @return local database file selected by the user.
	 */
	public File getSelectedLocalFile() {
		return localDBPath.getSelectedFile();
	}

	/**
	 * Sets the value for local database file entry.
	 * 
	 * @param file
	 *            current selection for local database file entry.
	 */
	public void setSelectedLocalFile(File file) {
		localDBPath.setSelectedFile(file);
	}

	/**
	 * Returns the host name entry for a remote database network service.
	 * <p>
	 * if {@link #getConnectionType()} is equals to {@link ConnectionType#LOCAL}
	 * it returns <code>null</code>, otherwise, the entry for host name field is
	 * returned.
	 * 
	 * @return host name entry for remote database network service.
	 */
	public String getHost() {
		return remoteConnectionInfoPanel.getHost();
	}

	/**
	 * Sets the value for a remote database network service host entry.
	 * 
	 * @param host
	 *            value to set for a remote database network service entry.
	 */
	public void setHost(String host) {
		remoteConnectionInfoPanel.setHost(host);
	}

	/**
	 * Returns the service name entry for a remote database network service.
	 * <p>
	 * if {@link #getConnectionType()} is equals to {@link ConnectionType#LOCAL}
	 * it returns <code>null</code>, otherwise, the entry for service name field
	 * is returned
	 * 
	 * @return service name entry for remote database network service.
	 */
	public String getServiceName() {
		return remoteConnectionInfoPanel.getServiceName();
	}

	/**
	 * Sets the value for a remote database network service name entry.
	 * 
	 * @param serviceName
	 *            value for remote network service name entry to set.
	 */
	public void setServiceName(String serviceName) {
		remoteConnectionInfoPanel.setServiceName(serviceName);
	}

	/**
	 * Returns the service port entry for a remote database network service.
	 * <p>
	 * if {@link #getConnectionType()} is equals to {@link ConnectionType#LOCAL}
	 * it returns <code>null</code>, otherwise, the entry for service port field
	 * is returned
	 * 
	 * @return service port entry for a remote database network service.
	 */
	public Integer getPort() {
		return remoteConnectionInfoPanel.getPort();
	}

	/**
	 * Sets the service port entry for a remote database network service.
	 * 
	 * @param port
	 *            new value for network service port entry.
	 */
	public void setPort(Integer port) {
		remoteConnectionInfoPanel.setPort(port);
	}

	/**
	 * Creates JPanel which holds remote network service configuration fields.
	 * 
	 * @return JPanel contains fields for network service configuration.
	 */
	private JPanel createRemoteInfoPanel() {
		return remoteConnectionInfoPanel;
	}

	/**
	 * Creates JPanel which holds the fields for local database configuration.
	 * 
	 * @return JPanel contains fields for local database configuration.
	 */
	private JPanel createLocalInfoPanel() {
		JPanel localInfoPanel = new JPanel(new FlowLayout());

		localInfoPanel.add(new JLabel("Database file path:"));
		localInfoPanel.add(localDBPath);
		return localInfoPanel;
	}

	/**
	 * initialize the GUI for this dialog and the actions.
	 */
	private void initComponents() {
		JPanel actionBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel footerPanel = new JPanel(new BorderLayout());
		connectButton = new JButton("Connect");
		JButton cancelButton = new JButton("Cancel");
		JPanel connectionInfoPanel;

		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!validator.validateEntry()) {
					errorMessageDisplay.setText(errorMessage);
				} else {
					option = Option.CONNECT;
					dispose();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				option = Option.CANCEL;
				dispose();
			}
		});

		footerPanel.setBorder(BorderFactory.createEtchedBorder());

		actionBar.add(connectButton);
		actionBar.add(cancelButton);

		errorMessageDisplay = new JTextField();
		errorMessageDisplay.setBorder(null);
		errorMessageDisplay.setEditable(false);
		errorMessageDisplay.setColumns(20);
		errorMessageDisplay.setFocusable(false);
		errorMessageDisplay.setForeground(Color.RED);
		footerPanel.add(errorMessageDisplay, BorderLayout.CENTER);

		footerPanel.add(actionBar, BorderLayout.EAST);
		add(footerPanel, BorderLayout.SOUTH);

		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		connectionInfoPanel =
				connectionType == ConnectionType.REMOTE
						? createRemoteInfoPanel() : createLocalInfoPanel();
		centerPanel.add(connectionInfoPanel);

		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Defines the strategy for validating connection configuration entries. The
	 * implementation is loaded according to connection type passed to dialog
	 * constructor.
	 */
	private interface Validator {

		/**
		 * Validate user entries and returns <code>true</code> if they are valid
		 * otherwise returns false.
		 * 
		 * @return <code>true</code> if entries are valid otherwise
		 *         <code>false</code>.
		 */
		boolean validateEntry();
	}

	/**
	 * A validator for remote connection entries.
	 */
	private class RemoteConnectionValidator implements Validator {

		/**
		 * Validates entries for connecting to a remote database service.
		 * 
		 * @return <code>true</code> if entries are valid, otherwise,
		 *         <code>false</code>.
		 */
		@Override
		public boolean validateEntry() {
			boolean validEntry = remoteConnectionInfoPanel.isValidEntry();

			if (!validEntry) {
				errorMessage = remoteConnectionInfoPanel.getErrorMessage();
				return false;
			}
			return true;
		}
	}

	/**
	 * A local connection validator.
	 */
	private class LocalConnectionValidator implements Validator {

		/**
		 * Validates if local database file is selected or not.
		 * 
		 * @return <code>true</code> if local database file is selected,
		 *         otherwise, <code>false</code>.
		 */
		@Override
		public boolean validateEntry() {
			boolean valid =
					localDBPath.getSelectedFile() == null ? false : true;
			errorMessage = valid ? null : "Select database file";
			return valid;
		}
	}
}
