package com.imdb.imdb.exception.enumuration;

public enum BusinessExceptionCode {
    GENERAL_ERROR(-1),
    SECURITY_UNHANDLED_EXCEPTION(-2),

    COMMAND_NOT_SUPPORTED(5003008),
    COMMAND_IMDB_SERVICE_ERROR(5004000),

    IMDB_NAME_BASE_NOT_FOUND(4001000),
    IMDB_TITLE_BASE_NOT_FOUND(4001001),
    IMDB_TITLE_RATING_NOT_FOUND(4001001)
    ;
    private int value;

    private ExceptionSeverity severity;

    BusinessExceptionCode(int value) {
        this.value = value;
        this.severity = ExceptionSeverity.Normal;
    }

    BusinessExceptionCode(int value, ExceptionSeverity severity) {
        this.value = value;
        this.severity = severity;
    }

    public static BusinessExceptionCode findByName(String name) {
        for (BusinessExceptionCode v : values()) {
            if (v.name() == name) {
                return v;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }

    public ExceptionSeverity getSeverity() {
        return severity;
    }
}
