package com.tacticalsoccer.exceptions;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorResponseFactory errorFactory;
    public GlobalExceptionHandler(ErrorResponseFactory errorFactory) {
        this.errorFactory = errorFactory;
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ProblemDetail handleDatabaseDown(DataAccessResourceFailureException ex) {
        return errorFactory.create(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Data service unavailable.",
                "The transaction could not be processed due to a connection failure with the bank.",
                "INFRA_DATABASE_OFFLINE"
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex) {

        String message = "Resource already registered";

        if (ex.getMessage() != null){
            if (ex.getMessage().contains("accounts_email_key")){
                message = "Email already in use";
            } else if (ex.getMessage().contains("accounts_username_key")){
                message = "Username already in use";
            }
        }

        return errorFactory.create(
                HttpStatus.CONFLICT,
                "Identity conflict",
                message,
                "ACCOUNT_002"
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail handleUnauthorized(InvalidCredentialsException ex){
        return errorFactory.create(
                HttpStatus.UNAUTHORIZED,
                "Invalid credentials",
                ex.getMessage(),
                "AUTH_001"
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleJsonError(HttpMessageNotReadableException ex){
        return errorFactory.create(
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request",
                "The request body is invalid. Check syntax, field names (case-sensitive), and data types.",
                "INFRA_JSON_001"
        );
    }

}