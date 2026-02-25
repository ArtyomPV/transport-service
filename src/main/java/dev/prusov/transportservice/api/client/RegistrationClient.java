package dev.prusov.transportservice.api.client;

public record RegistrationClient(
        String login,
        String password,
        String fullName
) {
}
