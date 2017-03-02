package suncertify.ui.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import suncertify.model.Contractor;

/**
 * A panel for displaying a single contractor information.
 * <p>
 * If {@link #getContractor()} returns <code>null</code>, the panel instance
 * displays empty fields.<br>
 * It also listens for property changes for set contractor instance and updates
 * the fields accordingly.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ContractorInfoPanel extends JPanel implements
		PropertyChangeListener {

	private JTextField locationField;
	private JTextField nameField;
	private JTextField ownerField;
	private JTextField rateField;
	private JTextField sizeField;
	private JList specialtiesList;
	private Contractor contractor;

	/**
	 * Construct a new panel.
	 */
	public ContractorInfoPanel() {
		initComponents();
	}

	/**
	 * Notification for properties values changes on set contractor instance.
	 * <p>
	 * When called, panel fields will be updated according to new contractor
	 * property values.
	 * 
	 * @param evt
	 *            property change event.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		fillFields();
	}

	/**
	 * Returns the contractor this panel is displaying information for.
	 * 
	 * @return displayed contractor information or <code>null</code> if not set.
	 */
	public Contractor getContractor() {
		return contractor;
	}

	/**
	 * Sets the contractor for which to display information and update the
	 * fields accordingly.
	 * 
	 * @param contractor
	 *            contractor to display its information.
	 */
	public void setContractor(Contractor contractor) {
		if (this.contractor != null) {
			this.contractor.removePropertyChangeListener(this);
		}
		this.contractor = contractor;

		if (this.contractor != null) {
			fillFields();
			this.contractor.addPropertyChangeListener(this);
		} else {
			clearFields();
		}
	}

	/**
	 * Clear all fields.
	 */
	private void clearFields() {
		nameField.setText("");
		locationField.setText("");
		ownerField.setText("");
		sizeField.setText("");
		rateField.setText("");
		specialtiesList.setListData(new String[0]);
	}

	/**
	 * Fill fields according to set set contractor.
	 */
	private void fillFields() {
		Integer owner = this.contractor.getOwner();

		nameField.setText(getTrimmedValue(this.contractor.getName()));
		locationField.setText(getTrimmedValue(this.contractor.getLocation()));
		ownerField.setText(owner == null ? "" : owner.toString());
		sizeField.setText(contractor.getSize().toString());
		rateField.setText(Contractor.RATE_FORMAT.format(this.contractor
				.getRate()));
		specialtiesList.setListData(this.contractor.getSpecialties());
	}

	/**
	 * Initialize UI.
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		JLabel label;
		JScrollPane listScroll;

		nameField = new JTextField();
		locationField = new JTextField();
		specialtiesList = new JList();
		sizeField = new JTextField();
		rateField = new JTextField();
		ownerField = new JTextField();

		setLayout(new GridBagLayout());

		label = new JLabel();
		label.setText("Name:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		nameField.setColumns(15);
		nameField.setEditable(false);
		nameField.setBackground(Color.WHITE);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = .5;
		add(nameField, gridBagConstraints);

		label = new JLabel();
		label.setText("Location:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		locationField.setColumns(15);
		locationField.setEditable(false);
		locationField.setBackground(Color.WHITE);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = .5;
		add(locationField, gridBagConstraints);

		label = new JLabel();
		label.setText("Specialties:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		listScroll = new JScrollPane();
		listScroll.setPreferredSize(new Dimension(120, 100));
		listScroll.setViewportView(specialtiesList);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = .5;
		add(listScroll, gridBagConstraints);

		label = new JLabel();
		label.setText("Size:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		sizeField.setColumns(6);
		sizeField.setEditable(false);
		sizeField.setBackground(Color.WHITE);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		add(sizeField, gridBagConstraints);

		label = new JLabel();
		label.setText("Rate:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		rateField.setColumns(8);
		rateField.setEditable(false);
		rateField.setBackground(Color.WHITE);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		add(rateField, gridBagConstraints);

		label = new JLabel();
		label.setText("Owner:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		add(label, gridBagConstraints);

		ownerField.setColumns(8);
		ownerField.setEditable(false);
		ownerField.setBackground(Color.WHITE);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		add(ownerField, gridBagConstraints);
	}

	/**
	 * Returns string value trimmed.
	 * 
	 * @param value
	 *            value to be trimmed.
	 * @return trimmed value.
	 */
	private String getTrimmedValue(String value) {
		return value == null ? "" : value.trim();
	}
}
