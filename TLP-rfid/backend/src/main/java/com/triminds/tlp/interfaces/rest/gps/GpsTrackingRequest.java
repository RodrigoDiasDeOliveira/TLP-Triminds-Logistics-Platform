package com.triminds.tlp.interfaces.rest.gps;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GpsTrackingRequest {

    @NotBlank
    private String vehicleId;

    @NotBlank
    private String shipmentId;

    @NotNull
    private Long companyId;
}