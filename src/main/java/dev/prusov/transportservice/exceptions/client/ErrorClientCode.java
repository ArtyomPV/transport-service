package dev.prusov.transportservice.exceptions.client;

import lombok.Getter;

@Getter
public enum ErrorClientCode {
    CLIENT_ALREADY_EXISTS("CLIENT", "Client already exists", 409);
    private final String code;
    private final String defaultMessage;
    private final int httpStatus;

    ErrorClientCode(String code, String defaultMessage, int httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }
}
