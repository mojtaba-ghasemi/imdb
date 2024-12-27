package com.imdb.imdb.exception;

import com.imdb.imdb.exception.enumuration.BusinessExceptionCode;

import java.io.Serializable;
import java.util.Optional;

public class IMDBException extends GeneralException implements Serializable {
    private BusinessExceptionCode businessExceptionCode;

    public IMDBException(BusinessExceptionCode businessExceptionCode) {
        super(businessExceptionCode.name(),
                MicroServices.IMDB_SERVICE.name(),
                businessExceptionCode.getValue(),
                ErrorCodeReaderUtil.getResourceProperity(businessExceptionCode.name()));
        this.businessExceptionCode = businessExceptionCode;
    }

    public IMDBException(BusinessExceptionCode businessExceptionCode, String exceptionDetails) {
        super(businessExceptionCode.name(),
                MicroServices.IMDB_SERVICE.name(),
                businessExceptionCode.getValue(),
                ErrorCodeReaderUtil.getResourceProperity(businessExceptionCode.name()));
        this.businessExceptionCode = businessExceptionCode;
        super.setExceptionDetails(exceptionDetails);
    }

    public IMDBException(Integer exceptionCode, String localeMessage) {
        super(MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage);
    }

    public IMDBException(Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage, exceptionDetails);
    }

    public IMDBException(String message, Integer exceptionCode, String localeMessage) {
        super(message, MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage);
    }

    public IMDBException(String message, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(message, MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage, exceptionDetails);
    }

    public IMDBException(String message, Throwable cause, Integer exceptionCode, String localeMessage) {
        super(message, cause, MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage);
    }

    public IMDBException(Throwable cause, Integer exceptionCode, String localeMessage, String exceptionDetails) {
        super(cause, MicroServices.IMDB_SERVICE.name(), exceptionCode, localeMessage, exceptionDetails);
    }

    public IMDBException(String localeMessage) {
        super(MicroServices.IMDB_SERVICE.name(), 400, localeMessage);
    }

    public Optional<BusinessExceptionCode> getBusinessExceptionCode() {
        return Optional.ofNullable(businessExceptionCode);
    }
}
