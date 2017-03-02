package suncertify.ui;

import java.text.NumberFormat;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

import suncertify.ui.LengthLimitFormatter.LengthLimitDocumentFilter;

/**
 * A formatter to control entries in a <code>JFormattedTextField</code> to be
 * only positive numbers.
 * <p>
 * This formatter convert <code>String</code> entries to <code>Integer</code>
 * values, prohibit entry for negative values, and to control the number of
 * digits to insert if applied.<br>
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
public class PositiveIntegerFormatter extends NumberFormatter {

	private int maximumLength = -1;

	/**
	 * Construct new formatter instance.
	 * <p>
	 * If passed <code>int</code> is less than or equal <code>0</code>, the
	 * checking for maximum number of digits is skipped.
	 * 
	 * @param maximumLength
	 *            maximum number of digits allowed, if less than or equal
	 *            <code>0</code>, checking for maximum digit is ignored.
	 */
	public PositiveIntegerFormatter(int maximumLength) {
		NumberFormat format = NumberFormat.getIntegerInstance();

		this.maximumLength = maximumLength;

		if (maximumLength > 0) {
			format.setMaximumIntegerDigits(maximumLength);
		}
		format.setGroupingUsed(false);
		format.setMaximumFractionDigits(0);
		format.setParseIntegerOnly(true);

		super.setFormat(format);
	}

	/*
	 * 
	 */
	@Override
	protected DocumentFilter getDocumentFilter() {
		PosIntDocumentFilter filter = new PosIntDocumentFilter();
		DocumentFilter originalFilter = super.getDocumentFilter();

		if (maximumLength > 0) {
			LengthLimitDocumentFilter sizeDocumentFilter =
					new LengthLimitDocumentFilter(maximumLength, originalFilter);

			filter.originalFilter = sizeDocumentFilter;
		} else {
			filter.originalFilter = originalFilter;
		}
		return filter;
	}

	/**
	 * Returns the maximum number of digits allowed by this formatter.
	 * 
	 * @return the maximumLength maximum number of digits allowed by this
	 *         formatter.
	 */
	public int getMaximumLength() {
		return maximumLength;
	}

	/**
	 * Document filter to control length of contents and using of negative sign.
	 * 
	 * @author Mohammad S. Abdellatif
	 * 
	 */
	private class PosIntDocumentFilter extends DocumentFilter {

		DocumentFilter originalFilter;

		/*
		 * 
		 */
		@Override
		public void insertString(FilterBypass fb, int offset, String string,
				AttributeSet attr) throws BadLocationException {
			string = string == null ? "" : string;
			if (string.startsWith("-")) {
				string = string.substring(1);
			}
			originalFilter.insertString(fb, offset, string, attr);
		}

		/*
		 * 
		 */
		@Override
		public void replace(FilterBypass fb, int offset, int length,
				String text, AttributeSet attrs) throws BadLocationException {
			text = text == null ? "" : text;
			if (text.startsWith("-")) {
				text = text.substring(1);
			}
			originalFilter.replace(fb, offset, length, text, attrs);
		}
	}

}
