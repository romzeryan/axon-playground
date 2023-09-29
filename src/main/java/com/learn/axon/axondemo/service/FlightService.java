package com.learn.axon.axondemo.service;

import java.util.List;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.learn.axon.axondemo.aggregate.FlightRepository;
import com.learn.axon.axondemo.event.FlightScheduledEvent;
import com.learn.axon.axondemo.model.FlightEntity;
import com.learn.axon.axondemo.query.GetAllFlightsQuery;
import com.learn.axon.axondemo.query.GetFlightQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

  private final FlightRepository flightRepository;
  private final ModelsMapper mapper;

  @EventHandler
  public void scheduleFlight(FlightScheduledEvent flightScheduledEvent) {
    log.info("Flight schedule event, flightId = {}", flightScheduledEvent.getFlightId());
    flightRepository.save(mapper.flightScheduledEventToEntity(flightScheduledEvent));
    log.info("Flight {} has been scheduled", flightScheduledEvent);
  }

  @QueryHandler
  public Optional<FlightEntity> getFlight(GetFlightQuery query) {
    log.info("Get Flight event, flightId = {}", query.getFlightId());
    return flightRepository.findById(query.getFlightId());
  }

  @QueryHandler
  public List<FlightEntity> getAllFlights(GetAllFlightsQuery query) {
    log.info("Get ALL Flights event");
    return flightRepository.findAll();
  }

}
