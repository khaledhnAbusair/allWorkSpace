package bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IbanValidators implements ConstraintValidator<Iban, String> {

	private Iban iban;

	@Override
	public void initialize(Iban constraintAnnotation) {

		this.iban = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return false;
	}

}
