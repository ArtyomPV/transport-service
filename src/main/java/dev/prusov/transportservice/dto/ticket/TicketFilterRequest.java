package dev.prusov.transportservice.dto.ticket;

public record TicketFilterRequest(
        String departureDateTime,
        String departurePoint,
        String destinationPoint,
        String carrierName,
        int page,
        int size
) {
}
