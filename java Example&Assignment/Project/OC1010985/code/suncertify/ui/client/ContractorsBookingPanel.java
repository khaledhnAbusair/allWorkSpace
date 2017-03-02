package suncertify.ui.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.BackingStoreException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import suncertify.facade.BookingException;
import suncertify.facade.BookingSystemFacade;
import suncertify.facade.FindException;
import suncertify.model.Contractor;
import suncertify.model.ContractorModel;
import suncertify.pref.UserPref;
import suncertify.ui.ErrorHandlerAction;
import suncertify.ui.LengthLimitFormatter;
import suncertify.ui.ThreadedAction;
import suncertify.ui.UIUtil;
import suncertify.ui.client.ContractorsTabularViewPanel.Column;
import suncertify.ui.client.ContractorsTabularViewPanel.ColumnConfig;

/**
 * An editor for booking contractors.
 * <p>
 * It uses {@link BookingSystemFacade} to list contractors from a persistence
 * storage according to user search criteria and booking a specific contractor.<br>
 * This panel displays contractors list in a tabular view, when user selects a
 * contractor from table, it will displays the details for this contractor in a
 * separate panel. Contractors list can be filter only according to name and/or
 * location.<br>
 * The only edit action allowed is booking which prompts the user to enter a
 * customer id to update a contractor booking information with.<br>
 * 
 * Actions available for this editor is returned by {@link #getActions()}.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ContractorsBookingPanel extends JPanel {

	private BookingSystemFacade bookingFacade;
	private Action searchAction = new SearchAction();
	private Action bookAction = new BookAction();
	private Action clearAction = new ClearAction();
	private ContractorInfoPanel contractorInfoPanel;
	private ContractorsTabularViewPanel tabularViewPanel;
	private JFormattedTextField nameFilterField;
	private JFormattedTextField locationFilterField;
	private JLabel statusBarLabel;
	private JCheckBox matchAllCheckBox;
	private Action[] actions;
	private Action[] configActions;
	private UserPref pref = new UserPref();

	/**
	 * Construct a new contractors booking panel.
	 * <p>
	 * This will instant a disabled panel because there is no facade assigned to
	 * it yet.
	 */
	public ContractorsBookingPanel() {
		this(null);
	}

	/**
	 * Construct a new contractors booking panel using
	 * <code>bookingFacade</code>.
	 * <p>
	 * 
	 * If passed facade is <code>null</code>, panel will be disabled, otherwise,
	 * it will be enabled.
	 * 
	 * @param bookingFacade
	 *            the contractor model.
	 */
	public ContractorsBookingPanel(BookingSystemFacade bookingFacade) {
		this.bookingFacade = bookingFacade;

		searchAction =
				new ThreadedAction(new ErrorHandlerAction(new SearchAction()));
		bookAction =
				new ThreadedAction(new ErrorHandlerAction(new BookAction()));
		clearAction = new ErrorHandlerAction(new ClearAction());

		actions = new Action[] { searchAction, clearAction, bookAction };
		configActions = new Action[] { new TabularViewConfigAction() };

		initComponents();
		updateEditorUI();
	}

	/**
	 * Returns booking facade associated with this panel.
	 * 
	 * @return booking facade associated with this panel.
	 */
	public BookingSystemFacade getBookingFacade() {
		return bookingFacade;
	}

	/**
	 * Sets booking facade to be used by this panel.
	 * 
	 * @param bookingFacade
	 *            facade to use.
	 */
	public void setBookingFacade(BookingSystemFacade bookingFacade) {
		this.bookingFacade = bookingFacade;
		updateEditorUI();
	}

	/**
	 * Returns actions supported by this editor.
	 * 
	 * @return actions supported by this editor.
	 */
	public Action[] getActions() {
		return actions;
	}

	/**
	 * Returns actions for which configure this panel.
	 * 
	 * @return action for configuring this panel
	 */
	public Action[] getConfigActions() {
		return configActions;
	}

	/**
	 * Update display message in status bar.
	 * 
	 * @param message
	 *            status bar new message
	 */
	private void updateStatusBar(String message) {
		statusBarLabel.setText(message);
	}

	/**
	 * Enable/disable editor components according to contractor model.
	 */
	private void updateEditorUI() {
		boolean validModel = this.bookingFacade != null;
		searchAction.setEnabled(validModel);
		clearAction.setEnabled(validModel);
		nameFilterField.setEditable(validModel);
		locationFilterField.setEditable(validModel);
		matchAllCheckBox.setEnabled(validModel);
		// default always null
		bookAction.setEnabled(false);
		tabularViewPanel.setContractors(new HashSet<Contractor>());
		contractorInfoPanel.setContractor(null);
	}

	/**
	 * Initialize UI.
	 */
	private void initComponents() {
		JPanel viewContainerPanel = new JPanel();
		setLayout(new BorderLayout());

		ColumnConfig[] configs = getVisibleColumns();
		tabularViewPanel = new ContractorsTabularViewPanel(configs);
		tabularViewPanel.addPropertyChangeListener("selectedContractor",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						Contractor selected = (Contractor) evt.getNewValue();

						contractorInfoPanel.setContractor(selected);

						if (selected != null) {
							Integer owner = selected.getOwner();

							bookAction.setEnabled(owner == null);
						} else {
							bookAction.setEnabled(false);
						}
					}
				});
		tabularViewPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(),
				BorderFactory.createTitledBorder("Contractors List")));

		viewContainerPanel.setLayout(new BorderLayout());

		viewContainerPanel.add(tabularViewPanel, BorderLayout.CENTER);

		viewContainerPanel.add(createDetailedViewPanel(), BorderLayout.NORTH);
		viewContainerPanel.add(tabularViewPanel, BorderLayout.CENTER);

		add(createFilterPanel(), BorderLayout.WEST);
		add(viewContainerPanel, BorderLayout.CENTER);

		statusBarLabel = new JLabel();
		statusBarLabel.setText("Ready");
		statusBarLabel.setBorder(new EtchedBorder());
		add(statusBarLabel, BorderLayout.SOUTH);
	}

	/**
	 * Gets the visible columns in contractors tabular view from user
	 * preferences.
	 * 
	 * @return visible columns from user preferences.
	 */
	private ColumnConfig[] getVisibleColumns() {
		Column[] visibleColumns = pref.getVisibleColumns();
		ColumnConfig[] configs = new ColumnConfig[visibleColumns.length];

		for (int i = 0; i < configs.length; i++) {
			Column column = visibleColumns[i];
			ColumnConfig config = new ColumnConfig();

			config.column = column;
			configs[i] = config;
		}

		return configs;
	}

	/**
	 * Creates the panel which holds filter fields and actions.
	 */
	private JPanel createFilterPanel() {
		JPanel filterContainer =
				new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel filterPanel = new JPanel(new GridBagLayout());
		JPanel commandsPanel = new JPanel();
		JButton clearButton = new JButton(clearAction);
		JButton searchButton = new JButton(searchAction);
		JLabel label;
		GridBagConstraints constraints;

		nameFilterField =
				new JFormattedTextField(new LengthLimitFormatter(
						Contractor.NAME_MAX_LENGTH));
		nameFilterField.setFocusAccelerator('N');
		locationFilterField =
				new JFormattedTextField(new LengthLimitFormatter(
						Contractor.LOCATION_MAX_LENGTH));
		locationFilterField.setFocusAccelerator('L');

		matchAllCheckBox = new JCheckBox("Match All");
		matchAllCheckBox.setSelected(false);
		matchAllCheckBox.setMnemonic('M');
		matchAllCheckBox.addActionListener(searchAction);

		nameFilterField.setAction(searchAction);
		locationFilterField.setAction(searchAction);

		filterContainer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(),
				BorderFactory.createTitledBorder("Search Filter")));

		label = new JLabel();
		label.setText("Name:");
		label.setDisplayedMnemonic('N');
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.EAST;
		filterPanel.add(label, constraints);

		nameFilterField.setColumns(15);
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		filterPanel.add(nameFilterField, constraints);

		label = new JLabel();
		label.setText("Location:");
		label.setDisplayedMnemonic('L');
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.EAST;
		filterPanel.add(label, constraints);

		locationFilterField.setColumns(15);
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		filterPanel.add(locationFilterField, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		filterPanel.add(matchAllCheckBox, constraints);

		commandsPanel.setBorder(BorderFactory.createEtchedBorder());
		commandsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		commandsPanel.add(searchButton);
		commandsPanel.add(clearButton);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		filterPanel.add(commandsPanel, constraints);

		filterContainer.add(filterPanel);

		return filterContainer;
	}

	/**
	 * Returns the panel which holds the detailed view for a contractor.
	 */
	private JPanel createDetailedViewPanel() {
		JPanel actionsBarPanel = new JPanel();
		JPanel contractorInfoPanelContainer = new JPanel();
		JButton bookButton = new JButton(bookAction);

		contractorInfoPanel = new ContractorInfoPanel();

		contractorInfoPanelContainer.setBorder(BorderFactory
				.createCompoundBorder(BorderFactory.createEtchedBorder(),
						BorderFactory.createTitledBorder("Details")));

		contractorInfoPanelContainer.setLayout(new BorderLayout());

		actionsBarPanel.setBorder(BorderFactory.createEtchedBorder());
		actionsBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		actionsBarPanel.add(bookButton);

		contractorInfoPanelContainer.add(contractorInfoPanel,
				BorderLayout.CENTER);
		contractorInfoPanelContainer.add(actionsBarPanel, BorderLayout.SOUTH);

		return contractorInfoPanelContainer;
	}

	/**
	 * Action for performing search action on contractors model according to
	 * filters fields.
	 */
	private class SearchAction extends AbstractAction {

		/**
		 * Construct a new search action.
		 */
		public SearchAction() {
			super("Search");
			putValue(Action.MNEMONIC_KEY, (int) 'S');
		}

		/**
		 * Perform search for contractors by calling
		 * {@link ContractorModel#find(suncertify.model.ContractorCriteria)}
		 * according to filter fields.
		 * 
		 * @param e
		 *            search action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Set<Contractor> contractors;
				String message;

				contractors =
						bookingFacade.findContractors(
								(String) nameFilterField.getValue(),
								(String) locationFilterField.getValue(),
								matchAllCheckBox.isSelected());
				tabularViewPanel.setContractors(contractors);
				message =
						"Last Refresh: "
								+ new SimpleDateFormat("dd/MM/yy HH:mm:ss")
										.format(new Date())
								+ ", Records Count: " + contractors.size();
				updateStatusBar(message);
			} catch (FindException ex) {
				UIUtil.handleActionFailure(ContractorsBookingPanel.this, ex);
			}

		}
	}

	/**
	 * Action for clearing filter fields, contractors tabular view, and
	 * contractor single detailed view.
	 */
	private class ClearAction extends AbstractAction {

		/**
		 * Construct a new action.
		 */
		public ClearAction() {
			super("Clear");
			putValue(Action.MNEMONIC_KEY, (int) 'C');
		}

		/**
		 * Clear editor filter fields, contractor tabular view, and contractor
		 * single detailed view.
		 * 
		 * @param e
		 *            clear action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Set<Contractor> contractors = new HashSet<Contractor>();

			nameFilterField.setValue("");
			locationFilterField.setValue("");
			tabularViewPanel.setContractors(contractors);
			matchAllCheckBox.setSelected(false);
			updateStatusBar("Ready");
		}
	}

	/**
	 * Action for booking a contractor.
	 */
	private class BookAction extends AbstractAction {

		/**
		 * Construct a new booking action.
		 */
		public BookAction() {
			super("Book");
			putValue(Action.MNEMONIC_KEY, (int) 'B');
		}

		/**
		 * Perform booking action by prompting user to enter customer id and
		 * update selected contractor booking information accordingly.
		 * 
		 * @param e
		 *            contractor booking action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JFormattedTextField ownerField;
				int option;
				Integer owner;

				ownerField =
						new JFormattedTextField(
								UIUtil.getIntegerFormatter(Contractor.OWNER_MAX_LENGTH));
				while (true) {
					option =
							JOptionPane.showOptionDialog(null, new Object[] {
									"Owner ID:", ownerField },
									"Enter Owner ID",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.PLAIN_MESSAGE, null, null,
									JOptionPane.OK_OPTION);

					owner = (Integer) ownerField.getValue();

					if (option != JOptionPane.OK_OPTION) {
						break;
					}

					if (owner != null) {
						Contractor contractor =
								tabularViewPanel.getSelectedContractor();
						int confirmOption;
						String confirmMessage =
								"Are you sure you want to book contractor '"
										+ contractor.getName() + "' in '"
										+ contractor.getLocation()
										+ "' for owner '" + owner + "'";

						confirmOption =
								JOptionPane.showConfirmDialog(null,
										confirmMessage, "Confirm booking",
										JOptionPane.OK_CANCEL_OPTION);
						if (confirmOption == JOptionPane.OK_OPTION) {
							bookingFacade.bookContractor(contractor, owner);
							setEnabled(false);
						}
						// always leave the loop
						break;
					}
					JOptionPane.showMessageDialog(null, "Please enter owner",
							"Invalid owner", JOptionPane.WARNING_MESSAGE);
				}
			} catch (BookingException ex) {
				UIUtil.handleActionFailure(ContractorsBookingPanel.this, ex);
			}
		}
	}

	/**
	 * Action for configuring visible columns for contractors table.
	 */
	private class TabularViewConfigAction extends AbstractAction {

		/**
		 * Construct new action.
		 */
		public TabularViewConfigAction() {
			super("Columns");
			putValue(Action.MNEMONIC_KEY, (int) 'U');
		}

		/**
		 * Display dialog with check boxes for each column, one the user clicks
		 * OK, contractors tabular view will be updated to view selected columns
		 * (check boxes) will be
		 * 
		 * @param e
		 *            action triggering event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Column[] columns = Column.valuesByDefaultIndex();
			JCheckBox[] checkBoxes = new JCheckBox[columns.length];
			List<ColumnConfig> currentConfig;
			int option;

			currentConfig = Arrays.asList(tabularViewPanel.getColumnsConfig());

			for (int i = 0; i < columns.length; i++) {
				Column column = columns[i];
				JCheckBox box = new JCheckBox();
				ColumnConfig config = new ColumnConfig();
				config.column = column;

				box.setText(column.name());
				box.setSelected(currentConfig.indexOf(config) >= 0 ? true
						: false);
				checkBoxes[i] = box;
			}
			while (true) {
				option =
						JOptionPane.showOptionDialog(
								ContractorsBookingPanel.this, new Object[] {
										"Check visible columns:", checkBoxes },
								"Columns configuration",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, null, null);
				if (option == JOptionPane.CANCEL_OPTION) {
					break;
				} else {
					ArrayList<Column> columnsChecked = new ArrayList<Column>();

					currentConfig = new ArrayList<ColumnConfig>();
					for (int i = 0; i < checkBoxes.length; i++) {
						JCheckBox box = checkBoxes[i];

						if (box.isSelected()) {
							ColumnConfig config = new ColumnConfig();

							config.column = columns[i];
							columnsChecked.add(columns[i]);
							currentConfig.add(config);
						}
					}
					if (currentConfig.size() > 0) {
						tabularViewPanel
								.setColumnsConfig(currentConfig
										.toArray(new ColumnConfig[currentConfig
												.size()]));
						pref.setVisibleColumns(columnsChecked
								.toArray(new Column[columnsChecked.size()]));
						try {
							pref.flush();
						} catch (BackingStoreException ex) {
							UIUtil.handleActionFailure(
									ContractorsBookingPanel.this, ex);
						}
						break;
					} else {
						JOptionPane.showMessageDialog(
								ContractorsBookingPanel.this,
								"You have to select at least one column",
								"Invalid entry", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		}
	}
}
