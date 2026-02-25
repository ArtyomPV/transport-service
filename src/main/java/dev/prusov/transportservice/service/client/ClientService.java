package dev.prusov.transportservice.service.client;

import dev.prusov.transportservice.api.client.RegistrationClient;
import dev.prusov.transportservice.dao.client.ClientDao;
import dev.prusov.transportservice.model.client.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientDao clientDao;

    public Client registration(RegistrationClient registrationClient) {
            return clientDao.save(registrationClient);
    }
}
