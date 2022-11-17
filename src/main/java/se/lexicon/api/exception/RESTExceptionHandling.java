package se.lexicon.api.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RESTExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataDuplicateException.class)
    protected ResponseEntity<Object> dataDuplicateException(DataDuplicateException ex) {
        System.out.println("ex.getMessage() = " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> globalException(Exception ex) {
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setStatusText("INTERNAL_ERROR:" + apiError.getTimestamp().toString());

        System.out.println("#####globalException---------------#####");
        System.out.println("########## " + apiError.getTimestamp());
        ex.printStackTrace();
        System.out.println("#####------------------------------#####");
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder details = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.append(error.getField());
            details.append(" ");
            details.append(error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, details.toString()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, "Malformed JSON request"));
    }

}