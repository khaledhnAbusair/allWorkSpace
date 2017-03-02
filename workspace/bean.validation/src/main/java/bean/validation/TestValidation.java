package bean.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class TestValidation {

	public static void main(String[] args) {

		Account account = new Account();
		account.setEmail("KHALED@GMAIL.COM");
		account.setAccountNumber("");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Account>> vaiolations = validator.validate(account);
		for (ConstraintViolation<Account> constraintViolation : vaiolations) {
			System.out.println("Message :"+constraintViolation.getMessage());
			System.out.println("Property :"+ constraintViolation.getPropertyPath());
		}
	}

}