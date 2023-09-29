package com.learn.axon.axondemo.event;

import java.time.ZonedDateTime;

import lombok.Value;

@Value
public class FlightScheduledEvent {
  String flightId;
  String from;
  String to;
  ZonedDateTime at;
  String captain;
}
