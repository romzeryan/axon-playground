package com.learn.axon.axondemo.command;

import java.time.ZonedDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class ScheduleFlightCommand {
    @TargetAggregateIdentifier String flightId;
    String from;
    String to;
    ZonedDateTime at;
    String captain;
}
