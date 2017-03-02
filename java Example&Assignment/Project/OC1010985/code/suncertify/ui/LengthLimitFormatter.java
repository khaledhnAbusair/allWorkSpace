package suncertify.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;

/**
 * A formatter to limit the number of characters allowed in a
 * <code>JFormattedTextField</code> to specific size.
 * <p>
 * An instance can not be shared between multiple text fields.
 * 
 * @author Mohammad S. Abdellatif
 */
public class LengthLimitFormatter extends DefaultFormatter {

	private int maximumSize;

	/**
	 * Construct a new formatter.
	 * 
	 * @param maximumSize
	 *            maximum number of characters allowed for a text field.
	 */
	public LengthLimitFormatter(int maximumSize) {
		this.maximumSize = maximumSize;
		setOverwriteMode(false);
	}

	/**
	 * Returns the filter to validate document contents size.
	 * 
	 * @return filter to validate document contents size.
	 */
	@Override
	protected DocumentFilter getDocumentFilter() {
		DocumentFilter originalDocumentFilter = super.getDocumentFilter();
		LengthLimitDocumentFilter filter =
				new LengthLimitDocumentFilter(maximumSize,
						originalDocumentFilter);

		return filter;
	}

	/**
	 * Filter to control the number of characters inserted/replaced in a
	 * document.
	 * 
	 * @author Mohammad S. Abdellatif
	 */
	public static class LengthLimitDocumentFilter extends DocumentFilter {

		private int maximumLength;
		private DocumentFilter originalDocumentFilter;

		/**
		 * Construct a new
		 * 
		 * @param maximumLength
		 */
		public LengthLimitDocumentFilter(int maximumLength,
				DocumentFilter originaDocumentFilter) {
			this.maximumLength = maximumLength;
			this.originalDocumentFilter = originaDocumentFilter;
		}

		/**
		 * Use <code>0</code> to <code>n</code> characters from
		 * <code>string</code> where <code>n</code> is the maximum number of
		 * characters can be taken from <code>string</code> so the text in
		 * document will not exceed the maximum number of characters allowed.
		 * 
		 * @param fb
		 *            to pass insert operation to document using it.
		 * @param offset
		 *            location of insertion.
		 * @param string
		 *            string to insert.
		 * @param attr
		 *            unique attributes.
		 * @throws BadLocationException
		 *             thrown to indicate a bad location in document.
		 */
		@Override
		public void insertString(FilterBypass fb, int offset, String string,
				AttributeSet attr) throws BadLocationException {
			String stringToUse =
					getStringToUse(string, fb.getDocument().getLength(),
							offset, 0);
			if (originalDocumentFilter == null) {
				fb.insertString(offset, stringToUse, attr);
			} else {
				originalDocumentFilter.insertString(fb, offset, stringToUse,
						attr);
			}
		}

		/**
		 * Use <code>0 to n</code> characters from <code>text</code> where
		 * <code>n</code> is the maximum number of characters can be taken from
		 * <code>text</code> so the text in document will not exceed the maximum
		 * number of characters allowed.
		 * 
		 * @param fb
		 *            to pass replace operation to document using it.
		 * @param offset
		 *            location of replace.
		 * @param length
		 *            number of characters to replace.
		 * @param text
		 *            text to replace with.
		 * @param attrs
		 *            unique attributes.
		 * @throws BadLocationException
		 *             thrown to indicate a bad location in document.
		 */
		@Override
		public void replace(FilterBypass fb, int offset, int length,
				String text, AttributeSet attrs) throws BadLocationException {
			String stringToUse =
					getStringToUse(text, fb.getDocument().getLength(), offset,
							length);
			if (originalDocumentFilter == null) {
				fb.replace(offset, length, stringToUse, attrs);
			} else {
				originalDocumentFilter.replace(fb, offset, length, stringToUse,
						attrs);
			}
		}

		/**
		 * Remove characters from document.
		 * 
		 * @param fb
		 *            to pass remove operation to document using it.
		 * @param offset
		 *            offset of removal.
		 * @param length
		 *            number of characters to remove.
		 * @throws BadLocationException
		 *             thrown to indicate a bad location in document.
		 */
		@Override
		public void remove(FilterBypass fb, int offset, int length)
				throws BadLocationException {
			if (originalDocumentFilter == null) {
				fb.remove(offset, length);
			} else {
				originalDocumentFilter.remove(fb, offset, length);
			}
		}

		/**
		 * Get the part of inserted/replaced text to use so the field will still
		 * have a valid maximum size.
		 * 
		 * 
		 * @param text
		 *            text to check.
		 * @param currentLength
		 *            the current length of document.
		 * @param offset
		 * @param lengthToRemove
		 *            <code>0</code> if insert, positive if replace.
		 * @return text to use.
		 */
		private String getStringToUse(String text, int currentLength,
				int offset, int lengthToRemove) {
			int newLength;

			text = text == null ? "" : text;
			newLength = currentLength + text.length() - lengthToRemove;
			if (newLength > maximumLength) {
				return lengthToRemove == 0 ? "" : text.substring(0,
						lengthToRemove);
			}
			return text;
		}
	}
}
