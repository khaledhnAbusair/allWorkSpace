/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.progressoft.jip.framework.CaseGenerator;
import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.simulation.impl.ui.casegenerator.TabluarComponent;
import com.progressoft.jip.simulation.impl.ui.casegenerator.ConstrainsParameter;
import com.progressoft.jip.simulation.impl.ui.implemn.SimulationCaseImple;
import com.progressoft.jip.simulation.impl.ui.implemn.SimulationCaseToGenrateCases;
import com.progressoft.jip.simulation.impl.ui.implemn.TypeTaskKey;
import com.progressoft.jip.simulation.impl.ui.implemn.VehicleCaseImple;
import com.progressoft.jip.simulation.impl.ui.implemn.VehicleCasesModel;
import com.progressoft.jip.simulation.impl.ui.simstate.SimulationStateDisplay;

/**
 * @author phi01tech
 *
 */
@SuppressWarnings("serial")
public class SwingCaseGenerator extends JPanel implements CaseGenerator {

	private static final Comparator<VehicleCase> VEHICLE_CASE_COMPARATOR = comparatorVehicleCase();
	private static final Insets FORM_INSETS = new Insets(5, 0, 0, 5);

	public Map<TypeTaskKey, Duration> taskDurations = new Hashtable<>();
	public List<VehicleCase> vehicleCases = new ArrayList<>();
	public JTextField parkingLotsCountField = new JTextField(10);
	public JTextField truckOffLoadDur = new JTextField(10);
	public JTextField vanOffLoadDur = new JTextField(10);
	public JTextField truckLoadDur = new JTextField(10);
	public JTextField vanLoadDur = new JTextField(10);
	public VehicleCasesModel tableModel;
	public int parkingLotsCount = 4;
	public Simulation simulation;
	public JTable casesTable;

	public SwingCaseGenerator(Simulation simulation) {
		this.setLayout(new GridBagLayout());
		this.simulation = simulation;
		addSimulationStart();
		addGeneralSetup();
		addCasesTable();
	}

	private void addSimulationStart() {
		JButton startButton = new JButton("Start Simualtion Vehicle");
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(startButton, constraints);

		btStartAction(startButton);

	}

	@Override
	public CaseGenerator setParkingLotsCount(int count) {
		parkingLotsCount = count;
		return this;
	}

	@Override
	public CaseGenerator addVehicleCase(VehicleCase vehicleCase) {
		this.vehicleCases.add(vehicleCase);
		Collections.sort(this.vehicleCases, VEHICLE_CASE_COMPARATOR);
		tableModel.fireTableDataChanged();
		return this;
	}

	@Override
	public CaseGenerator setTaskDuration(Type type, Task task, Duration duration) {
		TypeTaskKey key = new TypeTaskKey();
		key.task = task;
		key.type = type;
		taskDurations.put(key, duration);
		return this;
	}

	@Override
	public SimulationCase generateCase() {
		return new SimulationCaseToGenrateCases(this);
	}

	private static Comparator<VehicleCase> comparatorVehicleCase() {
		return (o1, o2) -> o1.arriveAfter().compareTo(o2.arriveAfter());
	}

	private void btStartAction(JButton startButton) {
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimulationCaseImple caseImple = new SimulationCaseImple(SwingCaseGenerator.this);
				simulation.start(caseImple);
				SimulationStateDisplay dialog = new SimulationStateDisplay(simulation);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

	private void btAddAction(JComboBox<Type> typeSelect, JComboBox<Task> taskSelect, JTextField arrivalDuration,
			JButton addButton) {
		addButton.addActionListener(a -> {
			Type type = (Type) typeSelect.getSelectedItem();
			Task task = (Task) taskSelect.getSelectedItem();
			Duration duration = Duration.ofSeconds(Integer.parseInt(arrivalDuration.getText()));
			addVehicleCase(new VehicleCaseImple(type, task, duration));
		});
	}

	private void btRemoveAction(JButton removeButton) {
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = casesTable.getSelectedRow();
				vehicleCases.remove(selectedRow);
				tableModel.fireTableDataChanged();
			}
		});
	}

	private void addGeneralSetup() {
		JPanel form = new JPanel();

		form.setLayout(new GridBagLayout());
		form.setBorder(new TitledBorder("Setup"));

		TabluarComponent component = new TabluarComponent();
		ConstrainsParameter cp = component.getParam();
		initGeneralConstrainsParam(cp);

		cp.setWeightx(0.2);
		component.addComp(form, new JLabel("Paking Lots count" + ":"), 0, 0);
		component.addComp(form, new JLabel("*Truck Load Duration" + ":"), 0, 1);
		component.addComp(form, new JLabel("*Truck Off-load Duration" + ":"), 0, 2);
		component.addComp(form, new JLabel("*Van Off-Load Duration" + ":"), 0, 4);
		component.addComp(form, new JLabel("*Van Load Duration" + ":"), 0, 3);
		component.addComp(form, new JLabel("*: in Seconds"), 0, 5);

		cp.setWeightx(0.8);
		component.addComp(form, parkingLotsCountField, 1, 0);
		component.addComp(form, truckLoadDur, 1, 1);
		component.addComp(form, truckOffLoadDur, 1, 2);
		component.addComp(form, vanLoadDur, 1, 3);
		component.addComp(form, vanOffLoadDur, 1, 4);
		cp.setWeightx(0.0);
		cp.setFill(GridBagConstraints.HORIZONTAL);
		cp.setAnchor(GridBagConstraints.NORTH);
		component.addComp(this, form, 0, 1);

	}

	private void initGeneralConstrainsParam(ConstrainsParameter cp) {
		cp.setGridx(0);
		cp.setGridy(0);
		cp.setInsets(FORM_INSETS);
		cp.setAnchor(GridBagConstraints.WEST);
		cp.setWeightx(0.2);
		cp.setFill(GridBagConstraints.NONE);

	}

	private void addCasesTable() {
		JPanel casesPanel = new JPanel(new BorderLayout());
		JPanel form1 = new JPanel();
		JComboBox<Type> typeSelect = new JComboBox<>(Type.values());
		JComboBox<Task> taskSelect = new JComboBox<>(Task.values());
		JTextField arrivalDuration = new JTextField(10);
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JPanel buttons = new JPanel();

		form1.setBorder(new TitledBorder("Vehicle Case"));
		form1.setLayout(new GridBagLayout());

		TabluarComponent comp = new TabluarComponent();
		ConstrainsParameter cp = comp.getParam();
		initGeneralConstrainsParam(cp);

		comp.addComp(form1, new JLabel("Type" + ":"), 0, 0);

		cp.setWeightx(0.2);
		comp.addComp(form1, new JLabel("Arrive after (seconds)" + ":"), 0, 2);

		cp.setWeightx(0.2);
		comp.addComp(form1, new JLabel("Task" + ":"), 2, 0);

		cp.setWeightx(0.8);
		comp.addComp(form1, typeSelect, 1, 0);

		cp.setWeightx(0.8);
		comp.addComp(form1, taskSelect, 2 + 1, 0);

		cp.setWeightx(0.8);
		comp.addComp(form1, arrivalDuration, 1, 2);

		cp.setAnchor(GridBagConstraints.EAST);
		cp.setGridwidth(4);

		comp.addComp(form1, buttons, 3, 4);
		buttons.add(removeButton);
		buttons.add(addButton);

		btRemoveAction(removeButton);
		btAddAction(typeSelect, taskSelect, arrivalDuration, addButton);

		JPanel form = form1;
		tableModel = new VehicleCasesModel(vehicleCases);
		casesTable = new JTable(tableModel);
		JTable table = casesTable;
		GridBagConstraints constraint = new GridBagConstraints();

		casesPanel.add(form, BorderLayout.NORTH);
		casesPanel.add(new JScrollPane(table), BorderLayout.CENTER);

		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = FORM_INSETS;
		constraint.anchor = GridBagConstraints.NORTH;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		add(casesPanel, constraint);
	}

}