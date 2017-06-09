package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Path("flight")
public class FlightController {

	@Inject
	private FlightService flightService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response find(@PathParam("id") int id) {
		Flight flight = new Flight();
		flight.setId(id);
		flight = flightService.find(flight);

		if (flight == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("Flight with id %d doesn't exist.", id)).build();
		}

		return Response.ok(flight).build();
	}

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Flight airline) {
		try {
			flightService.create(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot create Flight.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response update(Flight airline) {
		try {
			flightService.update(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot update Flight.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete")
	public Response delete(Flight airline) {
		try {
			flightService.delete(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot delete airline.").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> index() {
		return flightService.findAll();
	}

	@Path("flight/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getAirportbyFlight(@PathParam("name") String name) { 
		return flightService.findByNumber(name);

	}

}
