package com.learn.axon.axondemo.rest;

import java.time.ZonedDateTime;

public record ScheduleFlightRequest(
    String from,
    String to,
    ZonedDateTime at,
    String captain) {

}
