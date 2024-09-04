package com.platform.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.LOWEST_PRECEDENCE)
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler (ResourceNotFoundException.class)
    public @ResponseBody
    ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }


    @ExceptionHandler (ApiException.class)
    public @ResponseBody
    ResponseEntity<ExceptionResponse> handleApiException(ApiException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler (BadRequestException.class)
    public @ResponseBody
    ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler (ResourceAlreadyExistsException.class)
    public @ResponseBody
    ResponseEntity<ExceptionResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), String.valueOf(HttpStatus.CONFLICT.value()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse("Invalid request", String.valueOf(HttpStatus.BAD_REQUEST.value()), details);
        return ResponseEntity.status(status).body(exceptionResponse);
    }
}
