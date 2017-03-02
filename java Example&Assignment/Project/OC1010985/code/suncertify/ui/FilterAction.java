package suncertify.ui;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

/**
 * A base implementation for swing actions which want to add a behavior
 * dynamically to another swing action.
 * <p>
 * It only delegates calls to wrapped action instance.
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
public abstract class FilterAction implements Action {

	protected Action wrapped;

	/**
	 * Construct base wrapper for action <code>wrapped</code>.
	 * 
	 * @param wrapped
	 *            wrapped action.
	 */
	public FilterAction(Action wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * Calls wrapped action <code>actionPefromed</code>.
	 * 
	 * @param event
	 *            action triggering event.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		wrapped.actionPerformed(event);
	}

	/**
	 * Passes call to wrapped action <code>addPropertyChangeListener</code>
	 * method.
	 * 
	 * @param listener
	 *            listener to add.
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		wrapped.addPropertyChangeListener(listener);
	}

	/**
	 * Passes call to wrapped action <code>getValue</code> method.
	 * 
	 * @param key
	 *            key to get its value.
	 * @return returns the value related to passed key.
	 */
	@Override
	public Object getValue(String key) {
		return wrapped.getValue(key);
	}

	/**
	 * Passes call to wrapped action <code>isEnabled</code> method.
	 * 
	 * @return <code>true</code> if wrapped action is enabled, otherwise,
	 *         <code>false</code>.
	 */
	@Override
	public boolean isEnabled() {
		return wrapped.isEnabled();
	}

	/**
	 * Passes call to wrapped action <code>putValue</code> method.
	 * 
	 * @param key
	 *            key to assign value to.
	 * @param value
	 *            the value to be assigned for passed key.
	 */
	@Override
	public void putValue(String key, Object value) {
		wrapped.putValue(key, value);
	}

	/**
	 * Pass call to wrapped action <code>removePropertyChangeListener</code>
	 * method.
	 * 
	 * @param listener
	 *            listener to remove.
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		wrapped.removePropertyChangeListener(listener);
	}

	/**
	 * Pass call to wrapped action <code>setEnabled</code> method.
	 * 
	 * @param enabled
	 *            sets if to enable wrapped action or not.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		wrapped.setEnabled(enabled);
	}

}