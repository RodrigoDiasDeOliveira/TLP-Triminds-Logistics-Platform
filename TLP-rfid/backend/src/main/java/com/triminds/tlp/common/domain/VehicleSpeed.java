package com.triminds.tlp.common.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VehicleSpeed {

    private final double value;
    private final String unit;

    public static VehicleSpeed kmh(double value) {
        return new VehicleSpeed(value, "km/h");
    }
}