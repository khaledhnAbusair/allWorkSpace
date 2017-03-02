package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.NORTH;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.progressoft.jip.simulation.VehicleCase.Task;
import com.progressoft.jip.simulation.VehicleCase.Type;
import com.progressoft.jip.simulation.impl.ui.TabularComponent;

/**
 * @author u618
 *
 */
class GeneratorComponents {
	
	private TabularComponent tabular;
	private Component parkingLotsCountField;
	private Component truckLoadDur;
	private Component truckOffLoadDur;
	private Component vanLoadDur;
	private Component vanOffLoadDur;
	private SwingCaseGenerator swingCaseGenerator;
	private VehicleCasesModel tableModel;
	
	
	VehicleCasesModel getTableModel() {
		return this.tableModel;
	}

	GeneratorComponents(SwingCaseGenerator swingCaseGenerator) {
		this.swingCaseGenerator = swingCaseGenerator;
		this.parkingLotsCountField = swingCaseGenerator.parkingLotsCountField;
		this.truckLoadDur = swingCaseGenerator.truckLoadDur;
		this.truckOffLoadDur= swingCaseGenerator.truckOffLoadDur;
		this.vanLoadDur = swingCaseGenerator.vanLoadDur;     
		this.vanOffLoadDur = swingCaseGenerator.vanOffLoadDur; 
		this.tableModel = swingCaseGenerator.tableModel;
	}

	void addGeneralSetup() {
		JPanel form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setBorder(new TitledBorder("Setup"));

		tabular = new TabularComponent(form);

		addAllDurationTextBoxes();

		tabular = new TabularComponent(swingCaseGenerator);
		tabular.getConstrains().anchor = NORTH;
		tabular.addComponenetWithConstrains(0, 1, 0, form);

	}

	private void addAllDurationTextBoxes() {
		tabular.addComponenetWithConstrains(0, 0, 0.2, new JLabel("Paking Lots count" + ":"));
		tabular.addComponenetWithConstrains(1, 0, 0.8, parkingLotsCountField);
		tabular.addComponenetWithConstrains(0, 1, 0.2, new JLabel("*Truck Load Duration" + ":"));
		tabular.addComponenetWithConstrains(1, 1, 0.8, truckLoadDur);
		tabular.addComponenetWithConstrains(0, 2, 0.2, new JLabel("*Truck Off-load Duration" + ":"));
		tabular.addComponenetWithConstrains(1, 2, 0.8, truckOffLoadDur);
		tabular.addComponenetWithConstrains(0, 3, 0.2, new JLabel("*Van Load Duration" + ":"));
		tabular.addComponenetWithConstrains(1, 3, 0.8, vanLoadDur);
		tabular.addComponenetWithConstrains(0, 4, 0.2, new JLabel("*Van Off-Load Duration" + ":"));
		tabular.addComponenetWithConstrains(1, 4, 0.8, vanOffLoadDur);
		tabular.addComponenetWithConstrains(0, 5, 0, new JLabel("*: in Seconds"));
	}
	
	void addCasesTable() {
		JPanel casesPanel = new JPanel(new BorderLayout());
		JPanel form1 = new JPanel();
		JComboBox<Type> typeSelect = new JComboBox<>(Type.values());
		JComboBox<Task> taskSelect = new JComboBox<>(Task.values());
		JButton increaseSpeedButton = new JButton("+");
		JButton decreaseSpeedButton = new JButton("-");
		JLabel speedLabel = new JLabel("1.0");
		JTextField arrivalDuration = new JTextField(10);
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JPanel buttons = new JPanel();
		JPanel speedButtons = new JPanel();

		form1.setBorder(new TitledBorder("Vehicle Case"));
		form1.setLayout(new GridBagLayout());

		tabular = new TabularComponent(form1);

		tabular.addComponenetWithConstrains(0, 0, 0.2, new JLabel("Type" + ":"));
		tabular.addComponenetWithConstrains(1, 0, 0.8, typeSelect);
		tabular.addComponenetWithConstrains(2, 0, 0.2, new JLabel("Task" + ":"));
		tabular.addComponenetWithConstrains(2, 2, 0.2, new JLabel("Speed" + ":"));
		tabular.addComponenetWithConstrains(3, 0, 0.8, taskSelect);
		tabular.addComponenetWithConstrains(3, 2, 0.8, speedButtons);
		tabular.addComponenetWithConstrains(0, 2, 0.2, new JLabel("Arrive after (seconds)" + ":"));
		tabular.addComponenetWithConstrains(1, 2, 0.8, arrivalDuration);

		tabular.getConstrains().gridwidth = 4;
		tabular.getConstrains().anchor = EAST;

		tabular.addComponenetWithConstrains(0, 3, SwingCaseGenerator.DEFAULT_WEIGHT, buttons);

		speedButtons.add(increaseSpeedButton);
		speedButtons.add(decreaseSpeedButton);
		speedButtons.add(speedLabel);

		buttons.add(removeButton);
		buttons.add(addButton);

		increaseSpeedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swingCaseGenerator.simulation.incrementSpeed();
				double speed = swingCaseGenerator.simulation.getSpeed();
				speedLabel.setText(" " + speed);
			}
		});

		decreaseSpeedButton.addActionListener(e -> {
			swingCaseGenerator.simulation.decrementSpeed();
			double speed = swingCaseGenerator.simulation.getSpeed();
			speedLabel.setText(" " + speed);
		});

		removeButton.addActionListener(e -> {
			int selectedRow = swingCaseGenerator.casesTable.getSelectedRow();
			swingCaseGenerator.vehicleCases.remove(selectedRow);
			tableModel.fireTableDataChanged();
		});

		addButton.addActionListener(a -> {
			Type type = (Type) typeSelect.getSelectedItem();
			Task task = (Task) taskSelect.getSelectedItem();
			Duration duration = Duration.ofSeconds(Integer.parseInt(arrivalDuration.getText()));
			swingCaseGenerator.addVehicleCase(new VehicleCaseBean(type, task, duration));
		});
		
		JPanel form = form1;
		tableModel = new VehicleCasesModel(swingCaseGenerator.vehicleCases);
		JTable casesTable = new JTable(tableModel);
		JTable table = casesTable;
		
		casesPanel.add(form, BorderLayout.NORTH);
		casesPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		tabular = new TabularComponent(swingCaseGenerator);
		tabular.getConstrains().anchor = NORTH;
		tabular.getConstrains().fill = GridBagConstraints.HORIZONTAL;
		tabular.addComponenetWithConstrains(1, 1, 0, casesPanel);
		swingCaseGenerator.casesTable = casesTable;
	
	}


}
