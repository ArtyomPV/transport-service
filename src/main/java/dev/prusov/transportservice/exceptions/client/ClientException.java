package dev.prusov.transportservice.exceptions.client;


import lombok.Getter;

@Getter
public abstract class ClientException extends RuntimeException{

    private final ErrorClientCode errorClientCode;
    private final String details;


    protected ClientException(String message, ErrorClientCode error, String details) {
        super(message);
        this.errorClientCode = error;
        this.details = details;
    }
}
