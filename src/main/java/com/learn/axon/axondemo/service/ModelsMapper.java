package com.learn.axon.axondemo.service;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learn.axon.axondemo.command.ScheduleFlightCommand;
import com.learn.axon.axondemo.event.FlightScheduledEvent;
import com.learn.axon.axondemo.model.FlightEntity;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ERROR)
public interface ModelsMapper {

  FlightScheduledEvent flightScheduledCommandToEvent(ScheduleFlightCommand command);

  @Mapping(source = "flightId", target = "id")
  @Mapping(source = "at", target = "scheduledAt")
  @Mapping(source = "from", target = "fromAirport")
  @Mapping(source = "to", target = "toAirport")
  FlightEntity flightScheduledEventToEntity(FlightScheduledEvent event);

  default Instant mapDatetimeTypes(ZonedDateTime dt) {
    return dt.toInstant();
  }
}
