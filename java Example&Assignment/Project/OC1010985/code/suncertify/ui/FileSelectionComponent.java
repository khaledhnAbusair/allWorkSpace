package suncertify.ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Custom GUI component which consist of a text field and a button for browsing
 * and selection for a file in file system.
 * <p>
 * 
 * {@link #FileSelectionComponent()} instances will create a display an empty
 * text field and browse button with default label {@link #DEFUALT_BROWSE_LABEL}
 * , and both are enabled.
 * <p>
 * 
 * When browse button is clicked, a <code>JFileChooser</code> will be shown to
 * select a file. If a file is selected or the component was initially
 * constructed with a default file selection, the text field will display its
 * canonical path.
 * 
 * @author Mohammad S. Abdellatif
 */
public class FileSelectionComponent extends JPanel {

	/**
	 * The default label to use for browse button.
	 */
	public static final String DEFUALT_BROWSE_LABEL = "...";
	private static final int TEXT_FIELD_COLUMNS = 20;
	private JTextField textField;
	private boolean textFieldEnabled = true;
	private JFileChooser chooser = new JFileChooser();
	private JButton browseButton;

	/**
	 * Constructs a file selection GUI component with no file initially
	 * selected.
	 */
	public FileSelectionComponent() {
		this(DEFUALT_BROWSE_LABEL);
	}

	/**
	 * Constructs a file selection GUI component with initially file selected
	 * <code>selectedFile<code>.
	 * 
	 * <p>The text field for this component will be set to passed
	 * <code>selectedFile</code> canonical path and its browse button label with
	 * default value. If passed file is <code>null</code> it will be set empty.
	 * 
	 * @param selectedFile
	 *            file to be set as currently selected file.
	 */
	public FileSelectionComponent(File selectedFile) {
		this(DEFUALT_BROWSE_LABEL, selectedFile);
	}

	/**
	 * Constructs a file selection GUI component with browse button label
	 * <code>browseButtonLabel</code>.
	 * 
	 * @param browseButtonLabel
	 *            the label to be used for browse button.
	 */
	public FileSelectionComponent(String browseButtonLabel) {
		this(browseButtonLabel, null);
	}

	/**
	 * Constructs a file selection GUI component with initially file selected
	 * <code>selectedFile<code> and browse button label
	 * <code>browseButtonLabel</code>.
	 * 
	 * <p>
	 * The text field for this component will be set to passed
	 * <code>selectedFile</code> canonical path, if passed file is
	 * <code>null</code> it will be set empty.
	 * 
	 * @param browseButtonLabel
	 *            browse button label to be used.
	 * @param selectedFile
	 *            initially selected file.
	 */
	public FileSelectionComponent(String browseButtonLabel, File selectedFile) {

		browseButton = new JButton(browseButtonLabel);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		browseButton.setMargin(new Insets(0, 0, 0, 0));
		textField = new JTextField();
		textField.setEnabled(textFieldEnabled);
		textField.setColumns(TEXT_FIELD_COLUMNS);

		add(textField);
		add(browseButton);

		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int seletion = chooser.showOpenDialog(null);
				if (seletion == JFileChooser.APPROVE_OPTION) {
					setSelectedFile(chooser.getSelectedFile());
				}
			}
		});

		setSelectedFile(selectedFile);
		addPropertyChangeListener("enabled", new EnableChangeListener());
	}

	/**
	 * Set the number of columns to be displayed for this component text field.
	 * 
	 * @param columns
	 *            number of columns to be displayed for this component text
	 *            field.
	 */
	public void setTextFieldColumns(int columns) {
		textField.setColumns(columns);
	}

	/**
	 * Returns <code>true</code> if this component text field is editable,
	 * otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if this component text field is editable,
	 *         otherwise <code>false</code>.
	 */
	public boolean isTextFieldEditable() {
		return textField.isEditable();
	}

	/**
	 * Sets if this component text field is editable or not.
	 * 
	 * @param TextFieldEditable
	 *            if <code>true</code> make this component text field editable,
	 *            otherwise no.
	 */
	public void setTextFieldEditable(boolean TextFieldEditable) {
		textField.setEditable(TextFieldEditable);
	}

	/**
	 * Returns the label for browse button.
	 * 
	 * @return the label for browse button.
	 */
	public String getBrowseButtonLabel() {
		return browseButton.getText();
	}

	/**
	 * Sets the browse button label.
	 * 
	 * @param browseButtonLabel
	 *            browse button label to be set.
	 */
	public void setBrowseButtonLabel(String browseButtonLabel) {
		browseButton.setText(browseButtonLabel);
	}

	/**
	 * Returns the current selected file or <code>null</code> if no file is
	 * selected.
	 * <p>
	 * 
	 * It text field is Enabled and there was an entry in it, it would not be
	 * validated if it is a really path to a file, so the returned
	 * <code>File</code> instance can be a pointer for a file does not exist.
	 * 
	 * @return returns the current selected file or <code>null</code> if no file
	 *         is selected.
	 */
	public File getSelectedFile() {
		/*
		 * What really matters is the text in text field.
		 */
		String filePath = textField.getText();

		filePath = filePath == null ? "" : filePath.trim();
		if ("".equals(filePath)) {
			return null;
		}
		return new File(filePath);
	}

	/**
	 * Sets the selected file and relatively display its Canonical path in this
	 * component text field.
	 * <p>
	 * An empty string will be displayed if passed <code>selectedFile</code> is
	 * <code>null</code> or getting file canonical path is failed.
	 * 
	 * @param selectedFile
	 *            current file selection.
	 */
	public void setSelectedFile(File selectedFile) {
		try {
			textField.setText(selectedFile == null ? "" : selectedFile
					.getCanonicalPath());
		} catch (IOException ex) {
		}
	}

	/**
	 * Listen to <code>enable</code> property changes.
	 * 
	 * @author Mohammad S. Abdellatif
	 * 
	 */
	private class EnableChangeListener implements PropertyChangeListener {

		/**
		 * To capture the value change for property <code>enabled</code> to
		 * enabled/ disable subcomponents relatively.
		 * 
		 * @param evt
		 *            property change event.
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Boolean newValue = (Boolean) evt.getNewValue();
			browseButton.setEnabled(newValue);
			textField.setEditable(newValue);
		}
	}
}
