package suncertify.ui.client;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import suncertify.model.Contractor;

/**
 * Displays a list of contractors in a tabular view.
 * <p>
 * The number of columns to display and their labels can be configured by
 * {@link #setColumnsConfig(suncertify.ui.client.ContractorsTabularViewPanel.ColumnConfig[])}
 * or by passing columns configuration array through constructor.
 * 
 * Passing empty list to {@link #setContractors(Collection)} will clear the
 * table.<br>
 * {@link #getSelectedContractor()} returns the currently selected contractor in
 * table. Change of selected contractor can be caught by adding a property
 * change listener to listen on property <code>selectedContractor</code>.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ContractorsTabularViewPanel extends JPanel {

	private JTable contractorsTable;
	private ContractorsJTableModel tableModel = new ContractorsJTableModel();
	private List<Contractor> contractors = new ArrayList<Contractor>();
	private HashMap<Integer, ColumnConfig> columns =
			new HashMap<Integer, ColumnConfig>();
	private Contractor selectedContractor;

	/**
	 * Define a column in contractors tabular view.
	 */
	public static enum Column {

		/**
		 * Define column for <code>name</code>.
		 */
		NAME(0),
		/**
		 * Define column for <code>location</code>.
		 */
		LOCATION(1),
		/**
		 * Define column for <code>owner</code>.
		 */
		OWNER(2),
		/**
		 * Define column for <code>size</code>.
		 */
		SIZE(3),
		/**
		 * Define column for <code>rate</code>.
		 */
		RATE(4),
		/**
		 * Define column for <code>specialties</code>.
		 */
		SPECIALTIES(5);
		/**
		 * Holds the default index where this field is displayed.
		 */
		public final int defaultIndex;
		/**
		 * Holds the default label for this column.
		 */
		public final String defaultLabel;

		/**
		 * construct new column configuration.
		 * 
		 * @param index
		 *            index for column in table view.
		 */
		private Column(int index) {
			this.defaultIndex = index;
			this.defaultLabel =
					this.name().substring(0, 1)
							+ this.name().toLowerCase().substring(1);
		}

		/**
		 * Returns columns ordered by their default index.
		 * 
		 * @return columns ordered by default index.
		 */
		public static Column[] valuesByDefaultIndex() {
			Column[] columns = values();
			Arrays.sort(columns, new Comparator<Column>() {

				@Override
				public int compare(Column arg0, Column arg1) {
					return arg0.defaultIndex - arg1.defaultIndex;
				}
			});
			return columns;
		}

		/**
		 * Returns columns ordered by their names.
		 * 
		 * @return columns ordered by their names;
		 */
		public static Column[] valuesByName() {
			Column[] columns = values();
			Arrays.sort(columns, new Comparator<Column>() {

				@Override
				public int compare(Column arg0, Column arg1) {
					return arg0.name().compareTo(arg1.name());
				}
			});
			return columns;
		}
	}

	/**
	 * Defines a contractor column configuration.
	 */
	public static class ColumnConfig {

		/**
		 * The configured column.
		 */
		public Column column;
		/**
		 * The label for column. If <code>null</code> the default label will be
		 * used.
		 */
		public String label;

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ColumnConfig) {
				if (((ColumnConfig) obj).column == column) {
					return true;
				}
			}
			return false;
		}

		/*
		 * 
		 */
		@Override
		public int hashCode() {
			int hash = (this.column != null ? this.column.hashCode() : 0);
			hash = hash + (this.label != null ? this.label.hashCode() : 0);
			return hash;
		}
	}

	/**
	 * Construct a new contractors tabular view with the display for all columns
	 * in default order.
	 */
	public ContractorsTabularViewPanel() {
		Column[] columnsEnum = Column.values();

		for (Column column : columnsEnum) {
			ColumnConfig config = new ColumnConfig();

			config.column = column;
			config.label = column.defaultLabel;

			columns.put(column.defaultIndex, config);
		}
		initComponents();
	}

	/**
	 * Construct a new contractors tabular view with the display for columns
	 * configure in <code>configs</code>.
	 * <p>
	 * The order of displayed columns is the same of their passed configuration
	 * <code>configs</code>.
	 * 
	 * @param configs
	 *            configuration for displaying columns.
	 */
	public ContractorsTabularViewPanel(ColumnConfig[] configs) {
		for (int i = 0; i < configs.length; i++) {
			ColumnConfig config = configs[i];
			columns.put(i, config);
		}
		initComponents();
	}

	/**
	 * Returns the currently selected contractor.
	 * 
	 * @return currently selected contractor.
	 */
	public Contractor getSelectedContractor() {
		return selectedContractor;
	}

	/**
	 * Private method to set the currently selected contractor which also
	 * notifies for selected contractor change.
	 * 
	 * @param selectedContractor
	 *            contractor currently selected in table.
	 */
	private void setSelectedContractor(Contractor selectedContractor) {
		firePropertyChange("selectedContractor", this.selectedContractor,
				this.selectedContractor = selectedContractor);
	}

	/**
	 * Sets the list of contractors to display.
	 * <p>
	 * This will clear currently displayed contractors and sets currently
	 * selected contractor to <code>null</code>.
	 * 
	 * @param contractors
	 *            contractors list to display.
	 */
	public void setContractors(Collection<Contractor> contractors) {

		for (Contractor contractor : this.contractors) {
			contractor.removePropertyChangeListener(tableModel);
		}

		this.contractors.clear();
		this.contractors.addAll(contractors);

		for (Contractor contractor : contractors) {
			contractor.addPropertyChangeListener(tableModel);
		}

		setSelectedContractor(null);

		tableModel.fireTableDataChanged();
	}

	/**
	 * Sets the columns to display in the same passed configuration order with
	 * labels passed to it.
	 * <p>
	 * 
	 * @param configs
	 *            columns to display.
	 */
	public void setColumnsConfig(ColumnConfig[] configs) {
		columns.clear();
		for (int i = 0; i < configs.length; i++) {
			ColumnConfig columnConfig = configs[i];

			columns.put(i, columnConfig);
		}
		tableModel.fireTableStructureChanged();
	}

	/**
	 * Returns the currently used configuration for columns.
	 * 
	 * @return currently used configuration for columns.
	 */
	public ColumnConfig[] getColumnsConfig() {
		Collection<ColumnConfig> values = columns.values();
		return values.toArray(new ColumnConfig[values.size()]);
	}

	/**
	 * Initialize UI.
	 */
	private void initComponents() {
		JScrollPane scrollPane = new JScrollPane();

		setLayout(new BorderLayout());

		contractorsTable = new JTable();

		contractorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contractorsTable.setColumnSelectionAllowed(false);
		contractorsTable.setRowSelectionAllowed(true);
		contractorsTable.setModel(tableModel);
		contractorsTable.setDragEnabled(true);

		contractorsTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					/**
					 * this method will be called even when all data changed.
					 */
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = contractorsTable.getSelectedRow();
						if (selectedRow < 0) {
							setSelectedContractor(null);
						} else {
							setSelectedContractor(contractors.get(selectedRow));
						}
					}
				});

		scrollPane.setViewportView(contractorsTable);

		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * A <code>TableModel</code> for displaying a contractors fields in a
	 * <code>JTable</code>.
	 */
	private class ContractorsJTableModel extends AbstractTableModel implements
			PropertyChangeListener {

		/*
         * 
         */
		@Override
		public int getRowCount() {
			return contractors.size();
		}

		/*
         * 
         */
		@Override
		public int getColumnCount() {
			return columns.size();
		}

		/*
         * 
         */
		@Override
		public String getColumnName(int column) {
			ColumnConfig columnConfig = columns.get(column);
			String label = columnConfig.label;

			return label == null ? columnConfig.column.defaultLabel : label;
		}

		/*
         * 
         */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		/*
         * 
         */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Contractor contractor = contractors.get(rowIndex);
			ColumnConfig config = columns.get(columnIndex);
			Column column = config.column;
			String value = null;

			switch (column) {
			case NAME:
				value = contractor.getName();
				break;
			case LOCATION:
				value = contractor.getLocation();
				break;
			case OWNER:
				Integer owner = contractor.getOwner();
				value = owner == null ? "" : owner.toString();
				break;
			case RATE:
				value = Contractor.RATE_FORMAT.format(contractor.getRate());
				break;
			case SIZE:
				value = contractor.getSize() + "";
				break;
			case SPECIALTIES:
				value = Arrays.toString(contractor.getSpecialties());
			}
			return value == null ? "" : value.trim();
		}

		/**
		 * Listens for a contractor property change and update the contractor
		 * row accordingly.
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			int index = contractors.indexOf(evt.getSource());
			fireTableRowsUpdated(index, index);
		}
	}
}
