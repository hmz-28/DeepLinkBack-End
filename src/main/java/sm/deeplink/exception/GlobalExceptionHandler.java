package sm.deeplink.exception;

public class GlobalExceptionHandler  extends RuntimeException{


    public GlobalExceptionHandler(String message) {
        super(message);
    }
    public ErrorResponse handleNoRecordFoundException(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("No user Found");
        return errorResponse;
    }
}
