package org.example.whatsappbackend.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String code;

    public ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }
}
