package dev.prusov.transportservice.dao.ticket;

import dev.prusov.transportservice.dto.ticket.PaginatedTickets;
import dev.prusov.transportservice.dto.ticket.TicketFilterRequest;
import dev.prusov.transportservice.model.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDao {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public PaginatedTickets findAvailableTickets(TicketFilterRequest filter) {
        StringBuilder baseSql = new StringBuilder(
                "SELECT t.id as ticket_id, t.departure_date_time, t.seat_number, t.price, t.route_id " +
                        "FROM tickets as t " +
                        "LEFT JOIN routes as r ON t.route_id=r.id " +
                        "LEFT JOIN carriers as c ON r.carrier_id=c.id "
        );

        List<Object> params = new ArrayList<>();

        boolean firstConditionAdded = false;

        /*
         * Проверяем, что в запросе есть время отправки, если ранее не было добавлено условие
         * используем WHERE, иначе AND
         * В список добавляем время отправки
         */
        if (filter.departureDateTime() != null && !filter.departureDateTime().isEmpty()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            params.add(LocalDateTime.parse(filter.departureDateTime(), formatter));

            if (!firstConditionAdded) {
                baseSql.append(" WHERE t.departure_date_time >= ? ");
                firstConditionAdded = true;
            } else {
                baseSql.append(" AND t.departure_date_time >= ?");
            }
        }
        if (filter.departurePoint() != null && !filter.departurePoint().isEmpty()) {
            if (!firstConditionAdded) {
                baseSql.append(" WHERE r.departure_point LIKE ? ");
                firstConditionAdded = true;
            } else {
                baseSql.append(" AND r.departure_point LIKE ? ");
            }
            params.add("%" + filter.departurePoint() + "%");
        }

        if (filter.destinationPoint() != null && !filter.destinationPoint().isEmpty()) {
            if (!firstConditionAdded) {
                baseSql.append(" WHERE r.destination_point LIKE ? ");
                firstConditionAdded = true;
            } else {
                baseSql.append(" AND r.destination_point LIKE ? ");
            }
            params.add("%" + filter.destinationPoint() + "%");
        }

        if (filter.carrierName() != null && !filter.carrierName().isEmpty()) {
            if (!firstConditionAdded) {
                baseSql.append(" WHERE c.carrier_name LIKE ? ");
                firstConditionAdded = true;
            } else {
                baseSql.append(" AND c.carrier_name LIKE ? ");
            }
            params.add("%" + filter.carrierName() + "%");
        }

        String countSql = "SELECT COUNT(*) FROM (" + baseSql.toString() + ") AS filtered_results; ";

        Object[] countParams = params.toArray();
        long totalElements = jdbcTemplate.queryForObject(countSql, Long.class, countParams);

        int offset = filter.page() * filter.size();

        baseSql.append(" LIMIT ? OFFSET ?");

        params.add(filter.size());
        params.add(offset);

        Object[] finalSelectParams = params.toArray();
        List<Ticket> tickets = jdbcTemplate.query(baseSql.toString(), ticketRowMapper, finalSelectParams);

        int totalPages = (int) Math.ceil((double) totalElements / filter.size());
        return new PaginatedTickets(
                tickets,
                totalElements,
                totalPages,
                filter.page(),
                filter.size()
        );
    }

    @Override
    public List<Ticket> getAll() {
        String sql = "SELECT * FROM tickets;";
        return jdbcTemplate.query(sql, ticketRowMapper);
    }

    private static final RowMapper<Ticket> ticketRowMapper = ((rs, rowNum) -> {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("ticket_id"));
        if (rs.getTimestamp("departure_date_time") != null) {
            ticket.setDepartureDateTime(rs.getTimestamp("departure_date_time").toLocalDateTime());
        }
        ticket.setSeatNumber(rs.getInt("seat_number"));
        ticket.setPrice(rs.getBigDecimal("price"));
        ticket.setRouteId(rs.getLong("route_id"));
        return ticket;
    });
}
