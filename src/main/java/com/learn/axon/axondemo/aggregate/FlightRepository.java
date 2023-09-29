package com.learn.axon.axondemo.aggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.axon.axondemo.model.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {

}
