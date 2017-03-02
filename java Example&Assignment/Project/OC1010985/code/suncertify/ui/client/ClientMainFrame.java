package suncertify.ui.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import suncertify.facade.BookingSystemFacadeImpl;
import suncertify.model.ContractorModel;
import suncertify.ui.UIUtil;

/**
 * Main frame for client application to view and edit contractors information.
 * <p>
 * It takes a {@link ContractorModel} as the model for loading and storing
 * contractors information to a persistence storage.<br>
 * Uses {@link ContractorsBookingPanel} to load and edit contractors
 * information.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ClientMainFrame extends JFrame {

	private ExitAction exitAction = new ExitAction();
	private ContractorsBookingPanel editorPanel;
	private ContractorModel contractorModel;

	/**
	 * Creates new frame for contractors information view and edit with default
	 * <code>null</code> <code>ContractorModel</code>.
	 */
	public ClientMainFrame() {
		exitAction.setEnabled(false);

		setTitle("Booking System");

		editorPanel = new ContractorsBookingPanel();

		initComponents();
		pack();
		setMinimumSize(getSize());

		setLocation(UIUtil.getWindowCentralizedLocation(this));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// if connected to a database
				if (editorPanel.getBookingFacade() != null) {
					if (!confirmExit()) {
						return;
					}
				}
				dispose();
			}
		});
	}

	/**
	 * Set the model for contractors information.
	 * 
	 * @param contractorModel
	 *            contractors information model.
	 */
	public void setContractorModel(ContractorModel contractorModel) {
		this.contractorModel = contractorModel;
		exitAction.setEnabled(contractorModel != null);
		editorPanel.setBookingFacade(new BookingSystemFacadeImpl(
				contractorModel));
	}

	/**
	 * Returns the contractor information model.
	 * 
	 * @return contractor information model.
	 */
	public ContractorModel getContractorModel() {
		return contractorModel;
	}

	/**
	 * initialize the UI and actions.
	 */
	private void initComponents() {
		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		menuBar.add(createFileMenu());
		menuBar.add(createActionMenu());
		menuBar.add(createOptionsMenu());

		setLayout(new BorderLayout());
		editorPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		add(editorPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates file menu.
	 * 
	 * @return file menu.
	 */
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu();
		JMenuItem exit = new JMenuItem(exitAction);

		fileMenu.setMnemonic('f');
		fileMenu.setText("File");

		exit.setText("Close");
		// ALT + F4
		exit.setAccelerator(KeyStroke.getKeyStroke(115,
				InputEvent.ALT_DOWN_MASK));
		fileMenu.add(exit);

		return fileMenu;
	}

	/**
	 * Create action menu.
	 * 
	 * @return action menu.
	 */
	private JMenu createActionMenu() {
		JMenu actionMenu = new JMenu();
		Action[] actions = editorPanel.getActions();

		actionMenu.setText("Action");
		actionMenu.setMnemonic('A');

		for (Action action : actions) {
			actionMenu.add(action);
		}
		return actionMenu;
	}

	/**
	 * Creates options menu.
	 * 
	 * @return options menu.
	 */
	private JMenu createOptionsMenu() {
		JMenu configMenu = new JMenu();
		Action[] configActions = editorPanel.getConfigActions();

		configMenu.setText("Options");
		configMenu.setMnemonic('O');

		for (Action action : configActions) {
			configMenu.add(action);
		}

		return configMenu;
	}

	/**
	 * Displays a dialog to capture user confirmation regard exit from
	 * application or not.
	 * 
	 * @return <code>true</code> if user confirms exit, <code>false</code>
	 *         otherwise.
	 */
	private boolean confirmExit() {
		int selection =
				JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit", null,
						JOptionPane.OK_CANCEL_OPTION);
		if (selection == JOptionPane.OK_OPTION) {
			return true;
		}
		return false;
	}

	/**
	 * Exit from application action.
	 * <p>
	 * Displays exit confirmation for user, if user confirms the main frame will
	 * be disposed.
	 */
	private class ExitAction extends AbstractAction {

		/**
		 * Construct a new exit action.
		 */
		public ExitAction() {
			super("Exit");
		}

		/**
		 * Displays exit confirmation for user, if user confirms the main frame
		 * will be disposed.
		 * 
		 * @param e
		 *            exit action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (confirmExit()) {
				ClientMainFrame.this.dispose();
			}
		}
	}
}
