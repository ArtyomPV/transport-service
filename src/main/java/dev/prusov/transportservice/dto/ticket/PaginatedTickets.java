package dev.prusov.transportservice.dto.ticket;

import dev.prusov.transportservice.model.ticket.Ticket;

import java.util.List;

public record PaginatedTickets(
        List<Ticket> tickets,
        long totalElements,
        int totalPages,
        int currentPage,
        int pageSize
) {
}
