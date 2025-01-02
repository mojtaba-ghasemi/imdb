package com.imdb.imdb.exception;

public class RateLimitException extends RuntimeException {
    public RateLimitException(String s) {
        super(s);
    }
}
