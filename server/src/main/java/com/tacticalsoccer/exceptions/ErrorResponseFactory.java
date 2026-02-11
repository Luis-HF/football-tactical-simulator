package com.tacticalsoccer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class ErrorResponseFactory {

    public ProblemDetail create(HttpStatus status, String title, String detail, String errorCode) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setProperty("error_code", errorCode);
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }
}