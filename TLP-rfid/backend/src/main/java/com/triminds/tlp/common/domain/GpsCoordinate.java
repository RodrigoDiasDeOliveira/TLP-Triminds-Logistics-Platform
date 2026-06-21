package com.triminds.tlp.common.domain;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GpsCoordinate implements Serializable {

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private final double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private final double longitude;

    public static GpsCoordinate of(double latitude, double longitude) {
        return new GpsCoordinate(latitude, longitude);
    }
}