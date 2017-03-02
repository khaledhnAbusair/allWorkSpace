package suncertify.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Contains utility methods useful for GUI components.
 * 
 * @author Mohammad S. Abdellatif
 */
public final class UIUtil {

	/**
	 * Private constructor to disallow construction for this class because there
	 * is no need to instant it.
	 */
	private UIUtil() {
	}

	/**
	 * Constructs and returns a formatter can be used with formatted text fields
	 * which accepts network port entry.
	 * 
	 * @return port field formatter.
	 */
	public static NumberFormatter getPortFormatter() {
		return getIntegerFormatter(5);
	}

	/**
	 * Constructs then returns a formatter instance can be used with formatted
	 * text fields which accepts integer values.
	 * 
	 * @param maxIntegerDigits
	 *            maximum digits allowed in number integer part.
	 * @return a number formatter.
	 */
	public static NumberFormatter getIntegerFormatter(int maxIntegerDigits) {
		PositiveIntegerFormatter formatter =
				new PositiveIntegerFormatter(maxIntegerDigits);

		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		return formatter;
	}

	/**
	 * Calculates the point where <code>window</code> will resides at the center
	 * of screen.
	 * <p>
	 * 
	 * Calculation depends on screen size and insets and window width and
	 * height.
	 * 
	 * @param window
	 *            window for which to calculate its centralized location.
	 * @return the point which puts the window in a center centralized screen.
	 */
	public static Point getWindowCentralizedLocation(Window window) {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = defaultToolkit.getScreenSize();
		Insets insets =
				defaultToolkit.getScreenInsets(window
						.getGraphicsConfiguration());
		Dimension windowSize = window.getSize();
		int x =
				screenSize.width / 2 - insets.left - insets.right
						- windowSize.width / 2;
		int y =
				screenSize.height / 2 - insets.bottom - insets.top
						- windowSize.height / 2;

		return new Point(x, y);
	}

	/**
	 * Calculates the maximum size to display <code>window</code> in.
	 * <p>
	 * 
	 * The calculation depends on screen size and insets to get the most visible
	 * width and height to display window in.
	 * 
	 * @param window
	 *            window to which calculates maximum visible size.
	 * @return maximum visible size allowed to display the window in.
	 */
	public static Dimension getMaximumVisibleWindowSize(Window window) {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = defaultToolkit.getScreenSize();
		Insets insets =
				defaultToolkit.getScreenInsets(window
						.getGraphicsConfiguration());
		return new Dimension((int) screenSize.getWidth() - insets.right
				- insets.left, (int) screenSize.getHeight() - insets.bottom
				- insets.top);
	}

	/**
	 * Utility to handle exceptions thrown by UI actions by logging it and
	 * showing error dialog for it.
	 * 
	 * @param component
	 *            parent component to view error dialog in.
	 * @param exception
	 *            exception thrown.
	 * 
	 */
	public static void handleActionFailure(Component component,
			Exception exception) {
		Logger logger = Logger.getLogger("ActionFailure");
		logger.log(Level.WARNING, exception.getMessage(), exception);
		JOptionPane.showMessageDialog(component, exception.getMessage(),
				"Failure", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Utility to handle internal failures occurred while working on program UI.
	 * 
	 * @param throwable
	 *            cause of failure.
	 */
	public static void reportInternalFailure(Throwable throwable) {
		Logger logger = Logger.getLogger("InternalFailure");

		logger.log(Level.SEVERE, throwable.getMessage(), throwable);

		JOptionPane.showMessageDialog(null, new ErrorReportPanel(throwable),
				"Internal Error Report", JOptionPane.WARNING_MESSAGE);

	}

}
