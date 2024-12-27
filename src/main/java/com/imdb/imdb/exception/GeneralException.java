package com.imdb.imdb.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeneralException extends RuntimeException{
    private String microServiceName;
    private Integer exceptionCode;
    private String localeMessage;
    private String exceptionDetails;

    public GeneralException(String microServiceName, Integer exceptionCode, String localeMessage) {
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
    }

    public GeneralException(String microServiceName, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
        this.exceptionDetails = exceptionDetails;
    }

    public GeneralException(String message, String microServiceName, Integer exceptionCode, String localeMessage) {
        super(message);
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
    }

    public GeneralException(String message, String microServiceName, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(message);
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
        this.exceptionDetails = exceptionDetails;
    }

    public GeneralException(String message, Throwable cause, String microServiceName, Integer exceptionCode, String localeMessage) {
        super(message, cause);
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
    }

    public GeneralException(String message, Throwable cause, String microServiceName, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(message, cause);
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
        this.exceptionDetails = exceptionDetails;
    }

    public GeneralException(Throwable cause, String microServiceName, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(cause);
        this.microServiceName = microServiceName;
        this.exceptionCode = exceptionCode;
        this.localeMessage = localeMessage;
        this.exceptionDetails = exceptionDetails;
    }
}
