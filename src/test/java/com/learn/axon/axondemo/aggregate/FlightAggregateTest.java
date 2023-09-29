package com.learn.axon.axondemo.aggregate;

import java.time.ZonedDateTime;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learn.axon.axondemo.command.ScheduleFlightCommand;
import com.learn.axon.axondemo.event.FlightScheduledEvent;

class FlightAggregateTest {

  private FixtureConfiguration<FlightAggregate> flightFixture;

  @BeforeEach
  void setUp() {
    flightFixture = new AggregateTestFixture<>(FlightAggregate.class);
  }

  @Test
  void scheduleFlightCommand() {
    String flightId = "test";
    String from = "IST";
    String to = "AMS";
    String captain = "John Smith";
    ZonedDateTime flightTime = ZonedDateTime.now();

    flightFixture.givenNoPriorActivity()
        .when(new ScheduleFlightCommand(flightId, from, to, flightTime, captain))
        .expectSuccessfulHandlerExecution()
        .expectEvents(new FlightScheduledEvent(flightId, from, to, flightTime, captain));
  }
}