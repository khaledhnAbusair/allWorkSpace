package com.progressoft.jip.simulation.impl.ui.casegenerator;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JComponent;

public class TabluarComponent {

	private ConstrainsParameter parameter = new ConstrainsParameter();

	public TabluarComponent() {

	}

	public void addComp(JComponent panel, Component component, int gridx, int gridy) {
		parameter.setGridXY(gridx, gridy);
		panel.add(component, defineConstrains(this.parameter));

	}

	public ConstrainsParameter getParam() {
		return parameter;
	}

	private GridBagConstraints defineConstrains(Parameters cp) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = cp.getGridx();
		constraints.gridy = cp.getGridy();
		constraints.insets = cp.getInsets();
		constraints.anchor = cp.getAnchor();
		constraints.weightx = cp.getWeightx();
		constraints.fill = cp.getFill();
		return constraints;

	}

}
