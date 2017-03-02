package bean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
public @interface Iban {

	String message() default "{Iban is invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] countries();

}
