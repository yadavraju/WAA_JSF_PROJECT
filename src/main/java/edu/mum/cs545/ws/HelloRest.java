package edu.mum.cs545.ws;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

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
			result = "This is an airline: " + airline.getName();
		}
		return result;
	}
	
    //Just for testing purpose
	@POST
	@Path("/create/{param1}/{param2}")
	public String create(@PathParam("param1") String param1,
	                   @PathParam("param2") String param2) {
		return param1;

	}
}
