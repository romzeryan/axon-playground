package com.learn.axon.axondemo.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "flights")
@Data
public class FlightEntity {

  @Id
  private String id;

  @Column(name = "from_airport", nullable = false)
  private String fromAirport;

  @Column(name = "to_airport", nullable = false)
  private String toAirport;

  @Column(name = "scheduled_at", columnDefinition = "DATETIME")
  private Instant scheduledAt;

  @Column(nullable = false)
  private String captain;
}
