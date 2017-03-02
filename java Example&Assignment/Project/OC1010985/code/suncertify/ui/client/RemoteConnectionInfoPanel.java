package suncertify.ui.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import suncertify.ui.UIUtil;

/**
 * A panel to capture configuration for remote database connection. It contains
 * three fields, host, service name, and service port.
 * <p>
 * {@link #isValidEntry()} checks if user entries for fields are valid or not
 * and returns boolean value accordingly.
 * <p>
 * Entry values can be set/got through setter/getter methods related to each
 * entry.
 * 
 * @author Mohammad S. Abdellatif
 */
public class RemoteConnectionInfoPanel extends JPanel implements
		PropertyChangeListener {

	private JTextField hostField;
	private JFormattedTextField portField;
	private JTextField serviceNameField;

	/**
	 * Construct a new panel.
	 */
	public RemoteConnectionInfoPanel() {
		initInfoPanel();
		addPropertyChangeListener("enabled", this);
	}

	/**
	 * Checks if user entry is valid or not.
	 * <p>
	 * If returns <code>true</code>, {@link #getErrorMessage()} returns
	 * <code>null</code>.
	 * 
	 * @return <code>true</code> if entries are valid, otherwise,
	 *         <code>false</code>.
	 */
	public boolean isValidEntry() {
		return getErrorMessage() == null ? true : false;
	}

	/**
	 * Returns the error message suitable for user entries validity, returns
	 * <code>null</code> if they are valid.
	 * 
	 * @return suitable error message or <code>null</code> if entries are valid.
	 */
	public String getErrorMessage() {
		String host = hostField.getText();
		String serviceName = serviceNameField.getText();
		Integer port = (Integer) portField.getValue();

		if (host == null || host.trim().equals("")) {
			return "Host name is required";
		}
		if (serviceName == null || serviceName.trim().equals("")) {
			return "Service name is required";
		}
		if (port == null) {
			return "Service port is required";
		}
		return null;
	}

	/**
	 * Returns the service name entry.
	 * 
	 * @return service name entry.
	 */
	public String getServiceName() {
		return serviceNameField.getText();
	}

	/**
	 * Returns service port entry.
	 * 
	 * @return service port entry.
	 */
	public Integer getPort() {
		return (Integer) portField.getValue();
	}

	/**
	 * Returns service host entry.
	 * 
	 * @return service host entry.
	 */
	public String getHost() {
		String text = hostField.getText();
		return text;
	}

	/**
	 * Sets the value for service name entry to <code>serviceName</code>.
	 * 
	 * @param serviceName
	 *            new service name entry value.
	 */
	public void setServiceName(String serviceName) {
		serviceNameField.setText(serviceName);
	}

	/**
	 * Sets the value for service port entry to <code>port</code>.
	 * 
	 * @param port
	 *            new service port entry value.
	 */
	public void setPort(Integer port) {
		portField.setValue(port);
	}

	/**
	 * Sets the value for service host entry to <code>host</code>.
	 * 
	 * @param host
	 *            new host entry value.
	 */
	public void setHost(String host) {
		hostField.setText(host);
	}

	/**
	 * Initialize UI.
	 */
	private void initInfoPanel() {
		GridBagConstraints gridBagConstraints;
		JLabel label;
		Insets insets = new Insets(1, 1, 1, 1);

		setLayout(new GridBagLayout());

		label = new JLabel();
		label.setText("Host:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = insets;
		add(label, gridBagConstraints);

		hostField = new JTextField();
		hostField.setColumns(15);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.insets = insets;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		add(hostField, gridBagConstraints);

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
		label.setText("port:");
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
	 * Captures if <code>setEnabled</code> was cold on this panel to enable/
	 * disable panel entries accordingly.
	 * 
	 * @param evt
	 *            enable property change event.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Boolean newValue = (Boolean) evt.getNewValue();

		portField.setEditable(newValue);
		serviceNameField.setEditable(newValue);
		hostField.setEditable(newValue);
	}
}
