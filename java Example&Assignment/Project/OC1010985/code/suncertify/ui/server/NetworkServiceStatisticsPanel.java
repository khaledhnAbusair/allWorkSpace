package suncertify.ui.server;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import suncertify.db.server.NetworkService;
import suncertify.db.server.NetworkServiceEvent;
import suncertify.db.server.NetworkServiceEventListener;

/**
 * A panel for capturing and displaying a network service running status.
 * <p>
 * 
 * It has three fields, one for displaying network service start time, the other
 * for displaying when the service is stopped, and the third for displaying if
 * network service status (up,down).<br>
 * 
 * It is registered as a listener to a {@link NetworkService} instance to
 * capture start and stop events for it.
 * 
 * @author Mohammad S. Abdellatif
 * @see NetworkService
 */
public class NetworkServiceStatisticsPanel extends JPanel implements
		NetworkServiceEventListener {

	private static final String SERVICE_STATUS_UP = "UP";
	private static final String SERVICE_STATUS_DOWN = "DOWN";
	private JTextField serviceStatusField;
	private JFormattedTextField startTimeField;
	private JFormattedTextField stopTimeField;

	/**
	 * Construct a new network service statistics display panel.
	 */
	public NetworkServiceStatisticsPanel() {
		initComponents();
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		DefaultFormatterFactory formatterFactory =
				new DefaultFormatterFactory(new DateFormatter(simpleDateFormat));
		startTimeField.setFormatterFactory(formatterFactory);
		stopTimeField.setFormatterFactory(formatterFactory);
	}

	/**
	 * Notification when observed network service is started.
	 * <p>
	 * 
	 * When called, the status field value will be set to "UP", start time field
	 * will be filled with starting time stamp, and stop time field will be set
	 * to empty.
	 * 
	 * @param event
	 *            network service starting event.
	 */
	@Override
	public void serviceStarted(NetworkServiceEvent event) {
		serviceStatusField.setText(SERVICE_STATUS_UP);
		startTimeField.setValue(new Date());
		stopTimeField.setValue(null);
	}

	/**
	 * Notification when observed network service is stopped.
	 * <p>
	 * 
	 * When called, the status field value will be set to "DOWN" and the stop
	 * time field will be set to stop time stamp.
	 * 
	 * @param event
	 *            network service stop event.
	 */
	@Override
	public void serviceStopped(NetworkServiceEvent event) {
		serviceStatusField.setText(SERVICE_STATUS_DOWN);
		stopTimeField.setValue(new Date());
	}

	/**
	 * Initialize this panel GUI and actions.
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		JLabel label;

		serviceStatusField = new JTextField();
		startTimeField = new JFormattedTextField();
		stopTimeField = new JFormattedTextField();

		setLayout(new GridBagLayout());

		label = new JLabel();
		label.setText("Status:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		serviceStatusField.setColumns(4);
		serviceStatusField.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		add(serviceStatusField, gridBagConstraints);

		label = new JLabel();
		label.setText("Start Time:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		startTimeField.setColumns(18);
		startTimeField.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		add(startTimeField, gridBagConstraints);

		label = new JLabel();
		label.setText("Stop Time:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		stopTimeField.setColumns(18);
		stopTimeField.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		add(stopTimeField, gridBagConstraints);
	}
}
