package br.com.klok.desafio.mssale.config;

import java.util.List;
import java.util.stream.Collectors;

import br.com.klok.desafio.mssale.exception.EntityErrorDetails;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        var apiErrorDetails = new EntityErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR,
                                                    "Internal server error",
                                                            ex.getMessage());

        return new ResponseEntity<>(apiErrorDetails, apiErrorDetails.getStatus());
    }    

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers,
                                                                    HttpStatusCode status,
                                                                    WebRequest request) {

        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(x -> x.getDefaultMessage())
                                .collect(Collectors.toList());

        var apiErrorDetails = new EntityErrorDetails(HttpStatus.BAD_REQUEST, 
                                                            "Field validation error", 
                                                                    errors);

        return new ResponseEntity<>(apiErrorDetails, apiErrorDetails.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(EntityNotFoundException exception,
                                                                    WebRequest request) {

        EntityErrorDetails apiErrorDetails = new EntityErrorDetails(HttpStatus.NOT_FOUND,
                                                            "Entity not found",
                                                                    exception.getMessage());

        return new ResponseEntity<>(apiErrorDetails, new HttpHeaders(), apiErrorDetails.getStatus());
    }

}
    
