package de.ait_tr.DiaHelper.exception_handling;


import de.ait_tr.DiaHelper.exception_handling.exceptions.ProductNotFoundException;
import de.ait_tr.DiaHelper.exception_handling.exceptions.UserNotFoundException;
import de.ait_tr.DiaHelper.exception_handling.exceptions.UserWithThisEmailIsAlreadyRegisteredException;
import de.ait_tr.DiaHelper.exception_handling.exceptions.UserWithThisEmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleException(UserNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithThisEmailIsAlreadyRegisteredException.class)
    public ResponseEntity<Response> handleException(UserWithThisEmailIsAlreadyRegisteredException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserWithThisEmailNotFoundException.class)
    public ResponseEntity<Response> handleException(UserWithThisEmailNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleException(ProductNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
