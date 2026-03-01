package dev.prusov.transportservice.controller.ticket;

import dev.prusov.transportservice.dto.ticket.PaginatedTickets;
import dev.prusov.transportservice.dto.ticket.TicketFilterRequest;
import dev.prusov.transportservice.model.ticket.Ticket;
import dev.prusov.transportservice.service.ticket.TicketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("transport-service/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    public final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> tickets = ticketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/search")
    public ResponseEntity<PaginatedTickets> searchTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String departureDateTime,
            @RequestParam(required = false) String departurePoint,
            @RequestParam(required = false) String destinationPoint,
            @RequestParam(required = false) String carrierName
    ) {
        log.info("Start");
        TicketFilterRequest request = new TicketFilterRequest(
                departureDateTime,
                departurePoint,
                destinationPoint,
                carrierName,
                page,
                size);

        return ResponseEntity.ok(ticketService.searchTickets(request));
    }
}
