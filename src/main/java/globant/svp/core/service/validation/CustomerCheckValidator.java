package globant.svp.core.service.validation;

import globant.svp.core.domain.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerCheckValidator implements
		ConstraintValidator<CustomerCheck, Customer> {

	@Override
	public void initialize(CustomerCheck constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Customer customer, ConstraintValidatorContext context) {
		context.buildConstraintViolationWithTemplate("Unknown problem with the field.")
				.addPropertyNode("middleInitial").addConstraintViolation();
		
		context.buildConstraintViolationWithTemplate("Date of Birth is not valid.")
		.addPropertyNode("dob").addConstraintViolation();
		
		return true;
		
	}

}
