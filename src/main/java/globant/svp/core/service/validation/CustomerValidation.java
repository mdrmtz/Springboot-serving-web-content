package globant.svp.core.service.validation;

import globant.svp.core.exception.CustomerExistException;
import globant.svp.core.exception.ServerException;
import globant.svp.core.exception.ValidationException;
import globant.svp.rest.exception.ErrorMessage;

public class CustomerValidation implements Validation {

	@Validate
	public void validationMethod1() {
		ValidationException exception = new ValidationException();
		ErrorMessage message = new ErrorMessage(null, "Some First Attribute",
				"Some First Attribute validation failed", null);

		exception.addErrorMessages(message);
		System.out.println("Enter Here");
		throw exception;
	}

	@Validate
	public void validationMethod2() {
		ValidationException exception = new ValidationException();
		ErrorMessage message = new ErrorMessage(null, null,
				"Some other validation failed", null);

		exception.addErrorMessages(message);
		throw exception;
	}

	public void isCustomerExist() {
		throw new CustomerExistException();

	}

	public void validationMethodThatThrowServerError() {
		throw new ServerException();

	}

}
