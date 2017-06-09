package edu.mum.cs545.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;

@Named
@Path("airplane")
public class AirPlaneController {
	
	@Inject
	AirplaneService airplaneService;

	// this is not working in database
/*	@Path("create/{a}")
	@POST
	public String createAirPlane(@PathParam("a") String b) {
		System.out.println("--------" + b);
		Airline airline = airplaneService.findByName(b);
		if (airline != null) {
			airlineService.delete(airline);
		}
		return "Sucees";
	}

	@Path("fineone/{name}")
	@GET
	public Airline getAirLinesById(@PathParam("name") String name) {
		System.out.println("--------" + name);
		Airline airline = airplaneService.findByName(name);
		return airplaneService.find(airline);
	}

	@Path("flight/{name}")
	@GET
	public List<Airline> getAirLineFlight(@PathParam("name") String name) {
		System.out.println("--------" + name);
		List<Airline> findByFlight = new ArrayList<>();
		Airline airline = airplaneService.findByName(name);
		List<Flight> lFlight = airline.getFlights();
		for (Flight flight : lFlight) {
			findByFlight.addAll(airplaneService.findByFlight(flight));
		}
		return findByFlight;

	}*/
	
	@Path("flight/model/{name}")
	@GET
	public List<Airplane> getAirPlaneByModel(@PathParam("model")String model) {
		return airplaneService.findByModel(model);
	}

	@Path("list")
	@GET
	public List<Airplane> getAirlineListOfFlight() {
		List<Airplane> result = new ArrayList<>();
		if (airplaneService.findAll() != null) {
			result = airplaneService.findAll();
		}
		return result;
	}

}
