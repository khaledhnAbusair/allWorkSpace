package suncertify.ui;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.SwingUtilities;

/**
 * A wrapper for a swing <code>Action</code> which adds the behavior of
 * executing wrapped actions in a separate thread.
 * <p>
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
public class ThreadedAction extends FilterAction {

	/**
	 * Construct new wrapper.
	 * 
	 * @param wrapped
	 *            action to be executed in a separate thread.
	 */
	public ThreadedAction(Action wrapped) {
		super(wrapped);
	}

	/**
	 * Executes wrapped action in a separate thread.
	 * 
	 * @param event
	 *            action triggering event.
	 */
	public void actionPerformed(final ActionEvent event) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ThreadedAction.super.wrapped.actionPerformed(event);
			}
		});
	}
}
