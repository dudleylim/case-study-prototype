package com.apcargo.backend.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FieldException extends Exception {
    private Error error;
    private List<String> messages;

    public FieldException(Error error, String message, List<String> messages) {
        super(message);
        this.error = error;
        this.messages = messages;
    }
}
