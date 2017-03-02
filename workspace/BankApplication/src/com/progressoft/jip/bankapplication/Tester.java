/**
 * 
 */
package com.progressoft.jip.bankapplication;

import java.io.IOException;

/**
 * @author Anas Hamed & Khaled
 *
 */
public class Tester {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UI.interact();

		String iban = "JO94 CBJO 0010 0000 0000 0131 0003 02";
		UI.show(iban);

		 String purposeCode = "COLL";
		 UI.show(purposeCode);
		 if (PaymentPurpose.validatePaymentCode(purposeCode)) {
		 UI.show("Thank you for adding Payment purpse to your transaction process");
		 } else
		 UI.show("I`m sorry your purpose code is invalid ... Please try agin");

		if (IBANValidator.validateIBAN(iban))
			UI.show("IBAN checks out");
		else
			UI.show("IBAN invalid");
	}
}
