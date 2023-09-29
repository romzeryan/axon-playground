package com.learn.axon.axondemo.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.axon.axondemo.command.ScheduleFlightCommand;
import com.learn.axon.axondemo.model.FlightEntity;
import com.learn.axon.axondemo.query.GetAllFlightsQuery;
import com.learn.axon.axondemo.query.GetFlightQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  @PostMapping("/schedule")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void schedule(@RequestBody ScheduleFlightRequest scheduleFlightRequest) {
    String flightId = UUID.randomUUID().toString();
    commandGateway.send(getScheduleFlightRequest(flightId, scheduleFlightRequest));
  }

  @GetMapping("/{flightId}")
  public Optional<FlightEntity> getFlight(@PathVariable String flightId) {
    GetFlightQuery query = new GetFlightQuery(flightId);
    ResponseType<Optional<FlightEntity>> responseType = ResponseTypes.optionalInstanceOf(FlightEntity.class);
    // TODO: replace join() with project reactor response
    return queryGateway.query(query, responseType).join();
  }

  @GetMapping
  public List<FlightEntity> getAllFlights() {
    GetAllFlightsQuery query = new GetAllFlightsQuery();
    ResponseType<List<FlightEntity>> responseType = ResponseTypes.multipleInstancesOf(FlightEntity.class);
    // TODO: replace join() with project reactor response
    return queryGateway.query(query, responseType).join();
  }

  private ScheduleFlightCommand getScheduleFlightRequest(String flightId, ScheduleFlightRequest request) {
    return new ScheduleFlightCommand(flightId, request.from(), request.to(), request.at(), request.captain());
  }
}
