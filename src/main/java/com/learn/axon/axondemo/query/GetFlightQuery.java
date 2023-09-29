package com.learn.axon.axondemo.query;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetFlightQuery {

  String flightId;

}
