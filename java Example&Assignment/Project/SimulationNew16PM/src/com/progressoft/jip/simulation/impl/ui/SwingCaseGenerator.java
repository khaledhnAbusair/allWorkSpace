/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.framework.CaseGenerator;
import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;

/**
 * @author phi01tech
 *
 */
public class SwingCaseGenerator extends JPanel implements CaseGenerator {

	private static final Insets FORM_INSETS = new Insets(5, 0, 0, 5);
	private static final Comparator<VehicleCase> VEHICLE_CASE_COMPARATOR = ((o1, o2) -> o1.arriveAfter()
			.compareTo(o2.arriveAfter()));
	private int parkingLotsCount = 4;
	private List<VehicleCase> vehicleCases = new ArrayList<>();
	private Map<TypeTaskKey, Duration> taskDurations = new Hashtable<>();
	private VehicleCasesModel tableModel;
	private JTable casesTable;
	private JTextField parkingLotsCountField = new JTextField(10);
	private JTextField truckLoadDur = new JTextField(10);
	private JTextField truckOffLoadDur = new JTextField(10);
	private JTextField vanLoadDur = new JTextField(10);
	private JTextField vanOffLoadDur = new JTextField(10);
	private Simulation simulation;

	public SwingCaseGenerator(Simulation simulation) {
		this.setLayout(new GridBagLayout());
		this.simulation = simulation;
		addSimulationStart();
		addGeneralSetup();
		addCasesTable();
	}

	private void addSimulationStart() {
		JButton startButton = new JButton("Start");
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.NORTHEAST;

		this.add(startButton, constraints);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SimulationCaseImpl simCase = new SimulationCaseImpl();
				simulation.start(simCase);
				SimulationStateDisplay dialog = new SimulationStateDisplay(simulation);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

	private void addGeneralSetup() {
		JPanel form = new JPanel();
		GridBagConstraints constraint;

		form.setLayout(new GridBagLayout());
		form.setBorder(new TitledBorder("Setup"));

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = FORM_INSETS;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0.2;
		form.add(new JLabel("Paking Lots count" + ":"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = FORM_INSETS;
		constraints.weightx = 0.8;
		constraints.anchor = GridBagConstraints.WEST;
		form.add(parkingLotsCountField, constraints);
		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = FORM_INSETS;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0.2;
		form.add(new JLabel("*Truck Load Duration" + ":"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = FORM_INSETS;
		constraints.weightx = 0.8;
		constraints.anchor = GridBagConstraints.WEST;
		form.add(truckLoadDur, constraints);

		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = FORM_INSETS;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0.2;
		form.add(new JLabel("*Truck Off-load Duration" + ":"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = FORM_INSETS;
		constraints.weightx = 0.8;
		constraints.anchor = GridBagConstraints.WEST;
		form.add(truckOffLoadDur, constraints);
		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = FORM_INSETS;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0.2;
		form.add(new JLabel("*Van Load Duration" + ":"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = FORM_INSETS;
		constraints.weightx = 0.8;
		constraints.anchor = GridBagConstraints.WEST;
		form.add(vanLoadDur, constraints);
		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = FORM_INSETS;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0.2;
		form.add(new JLabel("*Van Off-Load Duration" + ":"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = FORM_INSETS;
		constraints.weightx = 0.8;
		constraints.anchor = GridBagConstraints.WEST;
		form.add(vanOffLoadDur, constraints);

		constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 5;
		constraint.insets = FORM_INSETS;
		constraint.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("*: in Seconds"), constraint);

		constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.insets = FORM_INSETS;
		constraint.anchor = GridBagConstraints.NORTH;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		this.add(form, constraint);

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
		GridBagConstraints constraints;

		form1.setBorder(new TitledBorder("Vehicle Case"));
		form1.setLayout(new GridBagLayout());

		GridBagConstraints constraints1 = new GridBagConstraints();

		constraints1.gridx = 0;
		constraints1.gridy = 0;
		constraints1.insets = FORM_INSETS;
		constraints1.anchor = GridBagConstraints.WEST;
		constraints1.weightx = 0.2;
		form1.add(new JLabel("Type" + ":"), constraints1);

		constraints1 = new GridBagConstraints();
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.insets = FORM_INSETS;
		constraints1.weightx = 0.8;
		constraints1.anchor = GridBagConstraints.WEST;
		form1.add(typeSelect, constraints1);
		GridBagConstraints constraints3 = new GridBagConstraints();

		constraints3.gridx = 2;
		constraints3.gridy = 0;
		constraints3.insets = FORM_INSETS;
		constraints3.anchor = GridBagConstraints.WEST;
		constraints3.weightx = 0.2;
		form1.add(new JLabel("Task" + ":"), constraints3);

		constraints3 = new GridBagConstraints();
		constraints3.gridx = 2 + 1;
		constraints3.gridy = 0;
		constraints3.insets = FORM_INSETS;
		constraints3.weightx = 0.8;
		constraints3.anchor = GridBagConstraints.WEST;
		form1.add(taskSelect, constraints3);
		GridBagConstraints constraints2 = new GridBagConstraints();

		constraints2.gridx = 0;
		constraints2.gridy = 2;
		constraints2.insets = FORM_INSETS;
		constraints2.anchor = GridBagConstraints.WEST;
		constraints2.weightx = 0.2;
		form1.add(new JLabel("Arrive after (seconds)" + ":"), constraints2);

		constraints2 = new GridBagConstraints();
		constraints2.gridx = 1;
		constraints2.gridy = 2;
		constraints2.insets = FORM_INSETS;
		constraints2.weightx = 0.8;
		constraints2.anchor = GridBagConstraints.WEST;
		form1.add(arrivalDuration, constraints2);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridwidth = 4;
		form1.add(buttons, constraints);

		buttons.add(removeButton);
		buttons.add(addButton);

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = casesTable.getSelectedRow();
				vehicleCases.remove(selectedRow);
				tableModel.fireTableDataChanged();
			}
		});

		addButton.addActionListener(a -> {
			Type type = (Type) typeSelect.getSelectedItem();
			Task task = (Task) taskSelect.getSelectedItem();
			Duration duration = Duration.ofSeconds(Integer.parseInt(arrivalDuration.getText()));
			addVehicleCase(new VehicleCase() {

				@Override
				public Type type() {
					return type;
				}

				@Override
				public Task task() {
					return task;
				}

				@Override
				public Duration arriveAfter() {
					return duration;
				}
			});
		});
		JPanel form = form1;
		tableModel = new VehicleCasesModel();
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
		int count = parkingLotsCount;
		Collection<VehicleCase> cases = Collections.unmodifiableCollection(vehicleCases);
		Map<TypeTaskKey, Duration> durations = Collections.unmodifiableMap(taskDurations);
		return new SimulationCase() {

			@Override
			public Iterable<VehicleCase> vehicleCases() {
				return cases;
			}

			@Override
			public int parkingLotsCount() {
				return count;
			}

			@Override
			public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
				TypeTaskKey key = new TypeTaskKey();
				key.task = vehicleTask;
				key.type = vehicleType;
				return durations.get(key);
			}
		};
	}

	private final class VehicleCasesModel extends AbstractTableModel {
		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "ID";
			case 1:
				return "Type";
			case 2:
				return "Task";
			case 3:
				return "Arrive After";
			}
			return null;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			List<VehicleCase> casesAsList = new ArrayList<>(vehicleCases);
			VehicleCase vehicleCase = casesAsList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return vehicleCase.identifier();
			case 1:
				return vehicleCase.type();
			case 2:
				return vehicleCase.task();
			case 3:
				return vehicleCase.arriveAfter();
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return vehicleCases.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}
	}

	private class TypeTaskKey {
		Type type;
		Task task;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((task == null) ? 0 : task.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TypeTaskKey other = (TypeTaskKey) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (task != other.task)
				return false;
			if (type != other.type)
				return false;
			return true;
		}

		private SwingCaseGenerator getOuterType() {
			return SwingCaseGenerator.this;
		}

	}

	private class SimulationCaseImpl implements SimulationCase {

		int parkingLotsCount = 0;
		Map<String, Duration> durations = new HashMap<>();
		List<VehicleCase> cases;

		SimulationCaseImpl() {
			parkingLotsCount = Integer.parseInt(parkingLotsCountField.getText());
			durations.put(Type.TURCK.name() + "_" + Task.LOAD.name(),
					Duration.ofSeconds(Integer.parseInt(truckLoadDur.getText())));
			durations.put(Type.TURCK.name() + "_" + Task.OFFLOAD.name(),
					Duration.ofSeconds(Integer.parseInt(truckOffLoadDur.getText())));
			durations.put(Type.VAN.name() + "_" + Task.LOAD.name(),
					Duration.ofSeconds(Integer.parseInt(vanLoadDur.getText())));
			durations.put(Type.VAN.name() + "_" + Task.OFFLOAD.name(),
					Duration.ofSeconds(Integer.parseInt(vanOffLoadDur.getText())));

			cases = new ArrayList<>(vehicleCases);
		}

		@Override
		public int parkingLotsCount() {
			return parkingLotsCount;
		}

		@Override
		public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
			return durations.get(vehicleType.name() + "_" + vehicleTask.name());
		}

		@Override
		public Iterable<VehicleCase> vehicleCases() {
			return cases;
		}

	}
}
