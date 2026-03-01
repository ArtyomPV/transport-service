package dev.prusov.transportservice.dao.ticket;

import dev.prusov.transportservice.dto.ticket.PaginatedTickets;
import dev.prusov.transportservice.dto.ticket.TicketFilterRequest;
import dev.prusov.transportservice.model.ticket.Ticket;

import java.util.List;

public interface TicketDao {
    PaginatedTickets findAvailableTickets(TicketFilterRequest filter);

    List<Ticket> getAll();
}
