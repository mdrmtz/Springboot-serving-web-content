package globant.svp.rest.exception;

import globant.svp.core.exception.CustomerExistException;
import globant.svp.core.exception.ServerException;
import globant.svp.core.exception.ValidationException;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionController {

	private MessageSource messageSource;

	@Autowired
	public RestExceptionController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessageCollection processFieldValidationException(
			MethodArgumentNotValidException ex) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessageCollection processValidationException(
			ValidationException ex) {

		return ex.getErrorMessageCollection();
	}

	@ExceptionHandler(ServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage processServerException(ServerException ex) {

		return new ErrorMessage(ErrorMessage.MESSAGE_TYPE_OTHER, null,
				ex.getMessage(), "Severe");
	}

	@ExceptionHandler(CustomerExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorMessage processCustomerExistError(CustomerExistException ex) {

		return new ErrorMessage("Error", "", ex.getMessage(), "");
	}

	private ErrorMessageCollection processFieldErrors(
			List<FieldError> fieldErrors) {
		ErrorMessageCollection errorMessageCol = new ErrorMessageCollection();

		for (FieldError fieldError : fieldErrors) {
			// String localizedErrorMessage =
			// resolveLocalizedErrorMessage(fieldError);
			errorMessageCol.addErrorMessages(ErrorMessage.MESSAGE_TYPE_FIELD,
					fieldError.getField(), fieldError.getDefaultMessage(),
					"Severe");
		}

		return errorMessageCol;
	}

	@SuppressWarnings("unused")
	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				currentLocale);

		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
}
