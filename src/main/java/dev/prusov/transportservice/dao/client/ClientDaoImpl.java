package dev.prusov.transportservice.dao.client;

import dev.prusov.transportservice.api.client.RegistrationClient;
import dev.prusov.transportservice.exceptions.client.ClientAlreadyExists;
import dev.prusov.transportservice.model.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@RequiredArgsConstructor
public class ClientDaoImpl implements ClientDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean ifExistsClientByLogin(String login) {
        String sql = "SELECT COUNT(*) FROM clients c WHERE c.login=?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, login);
        return count != null && count > 0;
    }

    @Override
    public Client save(RegistrationClient registrationClient) {
        String sql = "INSERT INTO clients (login, password, full_name) VALUES (?,?,?) RETURNING id;";

        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(
                    sql,
                    registrationClient.login(),
                    registrationClient.password(),
                    registrationClient.fullName()
            );
            Number id = (Number) result.get("id");
            if (id != null) {
                return Client.builder()
                        .id(id.longValue())
                        .login(registrationClient.login())
                        .password(registrationClient.password())
                        .fullName(registrationClient.fullName())
                        .build();
            }
            throw new RuntimeException("Database returned no ID for inserted client.");
        } catch (DuplicateKeyException e) {
            throw new ClientAlreadyExists(registrationClient.login());
        }


    }
}
