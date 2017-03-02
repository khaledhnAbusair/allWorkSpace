package suncertify.ui.server;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import suncertify.ui.FileSelectionComponent;
import suncertify.ui.UIUtil;

/**
 * A Panel which displays fields for configuring a network service.
 * <p>
 * 
 * Displays Three entries:
 * <ul>
 * <li>A database file path: to select a database file resides in file system.</li>
 * <li>A service name field: to capture network service name.</li>
 * <li>A service port field; to capture service port entry.</li>
 * </ul>
 * The validity for entries can be verified by {@link #isValidEntry()}, if
 * <code>true</code>, {@link #getErrorMessage()} returns <code>null</code>,
 * otherwise, it returns a text describes why the entries are not valid.
 * <p>
 * Configuration entry values can be set/got through setter/getter methods
 * related to each entry.
 * 
 * @author Mohammad S. Abdellatif
 */
public class NetworkServiceConfigPanel extends JPanel implements
		PropertyChangeListener {

	private static String SERVICE_NAME_REGEX = "[[\\w^_]/]*";
	private FileSelectionComponent databaseLocationField;
	private JFormattedTextField portField;
	private JTextField serviceNameField;

	/**
	 * Construct a new network service configuration panel.
	 */
	public NetworkServiceConfigPanel() {
		initComponents();
		addPropertyChangeListener("enabled", this);
	}

	/**
	 * Returns <code>true</code> if configuration entries are valid, otherwise,
	 * <code>false</code>.
	 * <p>
	 * 
	 * If <code>true</code>, {@link #getErrorMessage()} will return a text
	 * describes the why entries are not valid, otherwise it returns
	 * <code>null</code>.
	 * 
	 * @return <code>true</code> if configuration entries are valid, otherwise,
	 *         <code>false</code>.
	 */
	public boolean isValidEntry() {
		return getErrorMessage() == null ? true : false;
	}

	/**
	 * Returns the error message describes why user entries are not valid,
	 * returns <code>null</code> if else.
	 * 
	 * @return suitable error message or <code>null</code> if entries are valid.
	 */
	public String getErrorMessage() {
		File dbLocation = databaseLocationField.getSelectedFile();
		String serviceName = serviceNameField.getText();
		Integer port = (Integer) portField.getValue();

		if (dbLocation == null) {
			return "Select a database file";
		}
		if (serviceName == null || serviceName.trim().equals("")) {
			return "Service name is required";
		}
		if (!serviceName.matches(SERVICE_NAME_REGEX)
				|| serviceName.lastIndexOf('/') + 1 == serviceName.length()) {
			return "Service name can only contain characters and '/',"
					+ " no spaces is allowed, and '/' can not be at the end";
		}
		if (port == null) {
			return "Service port is required";
		}
		return null;
	}

	/**
	 * Returns the database file selected by the user.
	 * 
	 * @return database file selected by the user.
	 */
	public File getSelectedFile() {
		return databaseLocationField.getSelectedFile();
	}

	/**
	 * Returns the service name configuration entry.
	 * 
	 * @return service name configuration entry.
	 */
	public String getServiceName() {
		return serviceNameField.getText();
	}

	/**
	 * Returns service port configuration entry.
	 * 
	 * @return service port configuration entry.
	 */
	public Integer getPort() {
		return (Integer) portField.getValue();
	}

	/**
	 * Sets database file entry value to <code>file</code>.
	 * 
	 * @param file
	 *            database file value to set.
	 */
	public void setSelectedFile(File file) {
		databaseLocationField.setSelectedFile(file);
	}

	/**
	 * Sets service name entry value to <code>serviceName</code>.
	 * 
	 * @param serviceName
	 *            service name value to set.
	 */
	public void setServiceName(String serviceName) {
		serviceNameField.setText(serviceName);
	}

	/**
	 * Sets the service port entry value to <code>port</code>.
	 * 
	 * @param port
	 *            service port value to set.
	 */
	public void setPort(Integer port) {
		portField.setValue(port == null ? null : port);
	}

	/**
	 * Initialize this panel GUI and actions.
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		JLabel label;
		Insets insets = new Insets(1, 1, 1, 1);

		setLayout(new GridBagLayout());

		label = new JLabel();
		label.setText("Database Location:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = insets;
		add(label, gridBagConstraints);

		databaseLocationField = new FileSelectionComponent();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.insets = insets;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		add(databaseLocationField, gridBagConstraints);

		label = new JLabel();
		label.setText("Service Name:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = insets;
		add(label, gridBagConstraints);

		serviceNameField = new JTextField();
		serviceNameField.setColumns(10);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 4;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = insets;
		add(serviceNameField, gridBagConstraints);

		label = new JLabel();
		label.setText("Port:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = insets;
		add(label, gridBagConstraints);

		portField = new JFormattedTextField(UIUtil.getPortFormatter());
		portField.setColumns(5);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 4;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = insets;
		add(portField, gridBagConstraints);
	}

	/**
	 * Called when <code>setEnabled())</code> is called on this panel to
	 * enable/disable entries accordingly.
	 * 
	 * @param evt
	 *            <code>enabled</code> property change event.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Boolean newValue = (Boolean) evt.getNewValue();
		portField.setEditable(newValue);
		serviceNameField.setEditable(newValue);
		databaseLocationField.setEnabled(newValue);
	}
}
