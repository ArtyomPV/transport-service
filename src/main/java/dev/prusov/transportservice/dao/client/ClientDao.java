package dev.prusov.transportservice.dao.client;

import dev.prusov.transportservice.api.client.RegistrationClient;
import dev.prusov.transportservice.model.client.Client;

public interface ClientDao {
    Client save(RegistrationClient registrationClient);

    boolean ifExistsClientByLogin(String login);
}
