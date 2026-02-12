package com.tacticalsoccer.exceptions;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
                "Serviço de Dados Indisponível",
                "Não foi possível processar a operação devido a uma falha de conexão com o banco.",
                "INFRA_DATABASE_OFFLINE"
        );
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail handleResourceConflict(ResourceConflictException ex) {
        return errorFactory.create(
                HttpStatus.CONFLICT,
                "Conflito de Identidade",
                ex.getMessage(),
                "AUTH_RESOURCE_ALREADY_EXISTS"
        );
    }

    //@ExceptionHandler()
}