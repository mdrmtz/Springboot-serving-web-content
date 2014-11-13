package globant.svp.rest.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessageCollection {

    private List<ErrorMessage> errorMessages = new ArrayList<>();

    public ErrorMessageCollection() {

    }

    public void addErrorMessages(String type, String attributeName, String message, String severity) {
        ErrorMessage error = new ErrorMessage(type, attributeName, message, severity);
        errorMessages.add(error);
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
