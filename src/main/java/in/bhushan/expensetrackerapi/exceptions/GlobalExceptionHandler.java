package in.bhushan.expensetrackerapi.exceptions;

import in.bhushan.expensetrackerapi.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleException(MethodArgumentTypeMismatchException ex,WebRequest request){
        ErrorObject object = new ErrorObject();
        object.setStatusCode(HttpStatus.BAD_REQUEST.value());
        object.setMessage(ex.getMessage());
        object.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(object,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex,WebRequest request){
        ErrorObject object = new ErrorObject();
        object.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        object.setMessage(ex.getMessage());
        object.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(object,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
