package dev.prusov.transportservice.controller.client;

import dev.prusov.transportservice.api.client.RegistrationClient;
import dev.prusov.transportservice.model.client.Client;
import dev.prusov.transportservice.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("transport-service/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> registerClient(@RequestBody RegistrationClient registrationClient) {
        Client client = clientService.registration(registrationClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }
}
