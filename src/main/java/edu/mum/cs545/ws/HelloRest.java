package edu.mum.cs545.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@Named
@Path("hello")
public class HelloRest {

	@Inject
	private AirlineService airlineService;


	@GET
	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

	@Path("airline")
	@GET
	public String getAirlineTest() {
		String result = "Nil!";
		Airline airline = airlineService.findByName("oneworld");
		if (airline != null) {
			result = "This is an airline: " + airline.getId();
		}
		return result;
	}
	
	@Path("airline/create/{a}")
	@POST
	public String createAirline(@PathParam("a") String b) {
		System.out.println("--------"+b);
		String result = "Nil!";
		Airline airline = airlineService.findByName(b);
		if (airline != null) {
			airlineService.delete(airline);
		}
		return "Sucee";
	}
	
	@Path("airline/list")
	@GET
	public List<Airline> getAirlineListOfFlight() {
		List<Airline> result = new ArrayList<>();;
		
		if (airlineService.findAll() != null) {
			result = airlineService.findAll();
		}
		return result;
	}
	
    
	@POST
	@Path("/create/{param1}/{param2}")
	public String create(@PathParam("param1") String param1,
	                   @PathParam("param2") String param2) {
		return param1;

	}
}
