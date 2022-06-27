package it.triexercise.API_Custom_Queries_02.repositories;

import it.triexercise.API_Custom_Queries_02.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
