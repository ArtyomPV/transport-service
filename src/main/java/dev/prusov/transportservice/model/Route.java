package dev.prusov.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private long id;
    private String departurePoint;
    private String destinationPoint;
    private int durationMinutes;
    private long carrierId;
}
