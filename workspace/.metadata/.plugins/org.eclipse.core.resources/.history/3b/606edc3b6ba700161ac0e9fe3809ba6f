/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import static java.awt.GridBagConstraints.NORTHEAST;

import java.awt.GridBagLayout;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.progressoft.jip.simulation.CaseGenerator;
import com.progressoft.jip.simulation.Simulation;
import com.progressoft.jip.simulation.SimulationCase;
import com.progressoft.jip.simulation.SimulationFactory;
import com.progressoft.jip.simulation.VehicleCase;
import com.progressoft.jip.simulation.VehicleCase.Task;
import com.progressoft.jip.simulation.VehicleCase.Type;
import com.progressoft.jip.simulation.impl.ui.TabularComponent;
import com.progressoft.jip.simulation.impl.ui.statedisplay.SimulationStateDisplay;

/**
 * @author phi01tech
 *
 */

public class SwingCaseGenerator extends JPanel implements CaseGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1824339922902574239L;
	static final Comparator<VehicleCase> VEHICLE_CASE_COMPARATOR = ((o1, o2) -> o1.arriveAfter()
			.compareTo(o2.arriveAfter()));
	static final int DEFAULT_WEIGHT = 0;
	List<VehicleCase> vehicleCases = new ArrayList<>();
	JTextField parkingLotsCountField = new JTextField(10);
	JTextField truckLoadDur = new JTextField(10);
	JTextField truckOffLoadDur = new JTextField(10);
	JTextField vanLoadDur = new JTextField(10);
	JTextField vanOffLoadDur = new JTextField(10);
	VehicleCasesModel tableModel;
	JTable casesTable;
	Simulation simulation;
	
	private GeneratorComponents components;
	private int parkingLotsCount = 4;
	private Map<TypeTaskKey, Duration> taskDurations = new Hashtable<>();
	private TabularComponent tabular;
	private SimulationFactory factory;

	public SwingCaseGenerator(SimulationFactory factory) {
		this.setLayout(new GridBagLayout());
		this.factory = factory;
		this.components = new GeneratorComponents(this);
		addSimulationStart();
		components.addGeneralSetup();
		components.addCasesTable();
	}

	private void addSimulationStart() {
		tabular = new TabularComponent(this);
		tabular.getConstrains().anchor = NORTHEAST;
		JButton startButton = tabular.addComponenetWithConstrains(1, 0, 0, new JButton("Start"));
		startButton.addActionListener(e -> {
			SimulationCaseImpl simCase = new SimulationCaseImpl(this);
			this.simulation = factory.newWareHouseSimulation();
			simulation.start(simCase);
			SimulationStateDisplay dialog = new SimulationStateDisplay(simulation);
			dialog.pack();
			dialog.setVisible(true);
		});
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
		components.getTableModel().fireTableDataChanged();
		return this;
	}

	@Override
	public CaseGenerator setTaskDuration(Type type, Task task, Duration duration) {
		TypeTaskKey key = new TypeTaskKey(type, task);
		taskDurations.put(key, duration);
		return this;
	}

	@Override
	public SimulationCase generateCase() {
		return new SimulationCaseBean(vehicleCases, taskDurations, parkingLotsCount);
	}

}
