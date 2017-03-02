package suncertify.ui;

import java.awt.event.ActionEvent;

import javax.swing.Action;

/**
 * A Wrapper for a swing action which adds the behavior of a proper unified
 * handling for <code>Throwables</code> thrown by wrapped action.
 * <p>
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
public class ErrorHandlerAction extends FilterAction {

	/**
	 * Construct new error handling action instance.
	 * 
	 * @param wrapped
	 *            original swing action to be wrapped.
	 */
	public ErrorHandlerAction(Action wrapped) {
		super(wrapped);
	}

	/**
	 * Calls wrapped action <code>actionPefromed</code> and handle
	 * <code>Throwable</code>s if thrown by it.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			wrapped.actionPerformed(event);
		} catch (Throwable e) {
			UIUtil.reportInternalFailure(e);
		}
	}

}
