package com.learn.axon.axondemo.aggregate;

import static org.axonframework.modelling.command.AggregateCreationPolicy.CREATE_IF_MISSING;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import com.learn.axon.axondemo.command.ScheduleFlightCommand;
import com.learn.axon.axondemo.event.FlightScheduledEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Aggregate
@NoArgsConstructor
@Getter
@Log4j2
@Component
public class FlightAggregate {

  @AggregateIdentifier
  private String flightId;

  @CommandHandler
  @CreationPolicy(CREATE_IF_MISSING)
  public void on(ScheduleFlightCommand command) {
    log.info("Received {} command", command.getClass().getSimpleName());
    AggregateLifecycle.apply(getFlightScheduledEvent(command));
  }

  @EventSourcingHandler
  public void on(FlightScheduledEvent flightScheduledEvent) {
    this.flightId = flightScheduledEvent.getFlightId();
  }

  private static FlightScheduledEvent getFlightScheduledEvent(ScheduleFlightCommand command) {
    return new FlightScheduledEvent(
        command.getFlightId(),
        command.getFrom(),
        command.getTo(),
        command.getAt(),
        command.getCaptain()
    );
  }

}
