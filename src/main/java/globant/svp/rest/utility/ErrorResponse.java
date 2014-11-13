package globant.svp.rest.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorResponse {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResponseEntity generateErrorResponse(BindingResult result){
		return new ResponseEntity("Error in the field "
				+ result.getFieldError().getField(), HttpStatus.BAD_REQUEST);
	}

}
