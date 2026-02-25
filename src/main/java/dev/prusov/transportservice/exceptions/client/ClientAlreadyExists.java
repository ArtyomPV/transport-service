package dev.prusov.transportservice.exceptions.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientAlreadyExists extends ClientException{
    public ClientAlreadyExists(String login) {
        super(
                String.format("Client with login %s already exists", login),
                ErrorClientCode.CLIENT_ALREADY_EXISTS,
                String.format("Login: %s", login)
        );
    }
}
