package edu.mum.cs545.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Path("airline")
public class AirlineController {
	
	@Inject
	private AirlineService airlineService;

	//this is not working in database
	@Path("create/{a}")
	@POST
	public String createAirline(@PathParam("a") String b) {
		System.out.println("--------"+b);
		Airline airline = airlineService.findByName(b);
		if (airline != null) {
			airlineService.delete(airline);
		}
		return "Sucees";
	}
	
	@Path("fineone/{name}")
	@GET
	public Airline getAirLinesById(@PathParam("name") String name){
		System.out.println("--------"+name);
		Airline airline = airlineService.findByName(name);
		return airlineService.find(airline);
	}
	
	@Path("flight/{name}")
	@GET
	public List<Airline> getAirLineFlight(@PathParam("name") String name){
		System.out.println("--------"+name);
	    List<Airline> findByFlight = new ArrayList<>();
		Airline airline = airlineService.findByName(name);
		List<Flight> lFlight = airline.getFlights();
		for (Flight flight : lFlight) {
			 findByFlight.addAll(airlineService.findByFlight(flight));
		}
		return findByFlight;
		
	}
	
	
	@Path("list")
	@GET
	public List<Airline> getAirlineListOfFlight() {
		List<Airline> result = new ArrayList<>();
		if (airlineService.findAll() != null) {
			result = airlineService.findAll();
		}
		return result;
	}

}
