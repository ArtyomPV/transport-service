package dev.prusov.transportservice.service.ticket;

import dev.prusov.transportservice.dao.ticket.TicketDao;
import dev.prusov.transportservice.dto.ticket.PaginatedTickets;
import dev.prusov.transportservice.dto.ticket.TicketFilterRequest;
import dev.prusov.transportservice.model.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketDao ticketDao;

    public PaginatedTickets searchTickets(TicketFilterRequest request) {

        if (request.page() < 0 || request.size() <= 0) {
            throw new IllegalArgumentException("Invalid pagination parameters.");
        }

        return ticketDao.findAvailableTickets(request);
    }

    public List<Ticket> findAll() {
        return ticketDao.getAll();
    }
}
