package dev.prusov.transportservice.model.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private long id;
    private LocalDateTime departureDateTime;
    private int seatNumber;
    private BigDecimal price;
    private long routeId;
}
