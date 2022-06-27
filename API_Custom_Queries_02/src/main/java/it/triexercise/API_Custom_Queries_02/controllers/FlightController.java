package it.triexercise.API_Custom_Queries_02.controllers;

import it.triexercise.API_Custom_Queries_02.entities.Flight;
import it.triexercise.API_Custom_Queries_02.entities.FlightStatus;
import it.triexercise.API_Custom_Queries_02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    Random random = new Random();

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/addFlights")
    public void insertNumberOfFlights(@RequestParam(required = false) Integer numberOfFlights) {

        if (numberOfFlights == null || numberOfFlights == 0) {
            System.out.println("YA ");
            insertNumberOfFlights(100);
        } else {
            for (int i = 0; i < numberOfFlights; i++) {
                Flight flight = new Flight();

                String str = random.ints(48, 123)
                        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                        .limit(15)
                        .mapToObj(c -> (char) c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                        .toString();

                flight.setDescription(str);
                flight.setFromAirport(str);
                flight.setToAirport(str);
                flight.setFlightStatus(FlightStatus.values()[new Random().nextInt(FlightStatus.values().length)]);
                flightRepository.save(flight);
            }
        }
    }

    @GetMapping("/getFlights")
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
