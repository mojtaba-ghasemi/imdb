package com.imdb.imdb.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imdb.imdb.exception.enumuration.BusinessExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
@Slf4j
public class CaracalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static int BAD_REQUEST_EXCEPTION_CODE = 400;
    private final static int NOT_FOUND_EXCEPTION_CODE = 404;
    private final static int INTERNAL_SERVER_ERROR_EXCEPTION_CODE = 500;


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<RestErrorMessage> handleRestException(RuntimeException ex, WebRequest request) throws JsonProcessingException {

        logger.error("runtime exception", ex);

        String errorMessage = "";
        Integer code = -1;

        if (ex instanceof GeneralException) {
            GeneralException generalException = (GeneralException) ex;

            if (generalException.getExceptionCode() != null && generalException.getExceptionCode().toString().substring(0, 3).equals(String.valueOf(BAD_REQUEST_EXCEPTION_CODE))) {
                return new ResponseEntity<>(new RestErrorMessage(((GeneralException) ex).getLocaleMessage(),
                        ((GeneralException) ex).getExceptionCode()), HttpStatus.BAD_REQUEST);
            }
            if (generalException.getExceptionCode() != null && generalException.getExceptionCode().toString().substring(0, 3).equals(String.valueOf(NOT_FOUND_EXCEPTION_CODE))) {
                return new ResponseEntity<>(new RestErrorMessage(((GeneralException) ex).getLocaleMessage(),
                        ((GeneralException) ex).getExceptionCode()), HttpStatus.NOT_FOUND);
            }
            if (generalException.getExceptionCode() != null && generalException.getExceptionCode().toString().substring(0, 3).equals(String.valueOf(INTERNAL_SERVER_ERROR_EXCEPTION_CODE))) {
                return new ResponseEntity<>(new RestErrorMessage(((GeneralException) ex).getLocaleMessage(),
                        ((GeneralException) ex).getExceptionCode()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (generalException.getExceptionCode() != null && generalException.getExceptionCode().toString().substring(0, 3).equals(String.valueOf(HttpStatus.UNAUTHORIZED.value()))) {
                return new ResponseEntity<>(new RestErrorMessage(((GeneralException) ex).getLocaleMessage(),
                        ((GeneralException) ex).getExceptionCode()), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(new RestErrorMessage(((GeneralException) ex).getLocaleMessage(),
                        ((GeneralException) ex).getExceptionCode()), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(new RestErrorMessage(errorMessage, code), HttpStatus.BAD_REQUEST);
    }
}