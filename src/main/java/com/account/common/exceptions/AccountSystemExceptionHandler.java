package com.account.common.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.account.common.model.ErrorDetails;

@ControllerAdvice
@RestController
public class AccountSystemExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleAccountNotFoundException(
		  AccountNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}