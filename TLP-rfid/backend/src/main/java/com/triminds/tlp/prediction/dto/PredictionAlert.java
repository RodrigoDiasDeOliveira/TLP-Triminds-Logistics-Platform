package com.triminds.tlp.prediction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PredictionAlert {
    private final String shipmentId;
    private final String type;
    private final String message;
    private final int severity;


 
  } 