package com.progressoft.jip.simulation.impl.ui.casegenerator;

import java.awt.Insets;

public class ConstrainsParameter implements Parameters {
	private int gridx;
	private int gridy;
	private Insets insets;
	private int anchor;
	private double weightx;
	private int fill;
	private int gridwidth;

	public void setGridwidth(int gridwidth) {
		this.gridwidth = gridwidth;
	}

	public void setGridXY(int gridx, int gridy) {

		this.gridx = gridx;
		this.gridy = gridy;

	}

	@Override
	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	@Override
	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	@Override
	public Insets getInsets() {
		return insets;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	@Override
	public int getAnchor() {
		return anchor;
	}

	public void setAnchor(int anchor) {
		this.anchor = anchor;
	}

	@Override
	public double getWeightx() {
		return weightx;
	}

	public void setWeightx(double weightx) {
		this.weightx = weightx;
	}

	@Override
	public int getFill() {
		return fill;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}

	@Override
	public int gridwidth() {
		return gridwidth;
	}

}
