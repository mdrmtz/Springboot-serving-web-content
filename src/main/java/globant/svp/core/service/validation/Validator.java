package globant.svp.core.service.validation;

import globant.svp.core.exception.ValidationException;

import java.lang.reflect.Method;

public class Validator {

	private Validation validationClass;

	public Validator(Validation validationClass) {
		this.validationClass = validationClass;
	}

	public void validate() {

		ValidationException exception = new ValidationException();
		// AnnotationRunner runner = new AnnotationRunner();
		Method[] methods = validationClass.getClass().getMethods();

		for (Method method : methods) {
			Validate validate = method.getAnnotation(Validate.class);
			if (validate != null) {
				try {
					method.invoke(validationClass, Object.class);
				} catch (ValidationException ex) {

					exception.addErrorMessages(ex.getErrorMessage());
				} catch (Exception e) {
					exception.addErrorMessages(((ValidationException) e
							.getCause()).getErrorMessage());

				}
			}
		}

		if (exception.isErrorExist()) {

			throw exception;
		}
	}

}
