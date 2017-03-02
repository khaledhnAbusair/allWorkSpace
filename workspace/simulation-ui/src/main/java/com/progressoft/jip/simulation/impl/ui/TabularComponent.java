package com.progressoft.jip.simulation.impl.ui;

import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.WEST;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TabularComponent {
	private static final Insets FORM_INSETS = new Insets(5, 0, 0, 5);
	private static final int DEFAULT_HEIGHT_WIDTH = 1, DEFAULT_FILL = NONE;
	private GridBagConstraints constrains;
	private JPanel panel;

	public TabularComponent(JPanel panel) {
		this.constrains = new GridBagConstraints();
		this.panel = panel;
		setBagDefaults();

	}

	public GridBagConstraints getConstrains() {
		return this.constrains;
	}
	
	
	public<C extends JComponent> C addJComponentDarkGrayBorder(C component){
		component.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.add(component);
		return component;
	}

	public <C extends Component> C addComponenetWithConstrains(int gridx, int gridy, double weightx, C component) {
		constrains.gridx = gridx;
		constrains.gridy = gridy;
		constrains.weightx = weightx;
		panel.add(component, constrains);
		return component;
	}

	public void setBagDefaults() {
		constrains.gridheight = DEFAULT_HEIGHT_WIDTH;
		constrains.gridwidth = DEFAULT_HEIGHT_WIDTH;
		constrains.fill = DEFAULT_FILL;
		constrains.anchor = WEST;
		constrains.insets = FORM_INSETS;
	}

}
