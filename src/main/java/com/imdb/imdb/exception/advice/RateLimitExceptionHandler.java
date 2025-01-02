package com.imdb.imdb.exception.advice;


import com.imdb.imdb.dto.RateLimitErrorMessageDto;
import com.imdb.imdb.exception.ChangePasswordConstraintException;
import com.imdb.imdb.exception.RateLimitException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RateLimitExceptionHandler {

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<Object> handleRateLimitException(RateLimitException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new RateLimitErrorMessageDto(ex.getMessage(), 429));
    }

    @ExceptionHandler(ChangePasswordConstraintException.class)
    public ResponseEntity<Object> handleChangePasswordConstraintException(ChangePasswordConstraintException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}

