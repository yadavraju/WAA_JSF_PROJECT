package edu.mum.cs545.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import edu.mum.gf.workaround.Utills;

@Path("airline")
public class AirlineController {

	@Inject
	private AirlineService airlineService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response find(@PathParam("id") int id) {
		Airline airline = new Airline();
		airline.setId(id);
		airline = airlineService.find(airline);

		if (airline == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("Airline with id %d doesn't exist.", id)).build();
		}

		return Response.ok(airline).build();
	}

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Airline airline) {
		try {
			airlineService.create(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot create airline.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response update(Airline airline) {
		try {
			airlineService.update(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot update airline.").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete")
	public Response delete(Airline airline) {
		try {
			airlineService.delete(airline);
			return Response.ok(Utills.sucessMesseesge()).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot delete airline.").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> index() {
		return airlineService.findAll();
	}

	@Path("flight/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> getAirLineFlight(@PathParam("name") String name) {
		System.out.println("--------" + name);
		List<Airline> findByFlight = new ArrayList<>();
		Airline airline = airlineService.findByName(name);
		List<Flight> lFlight = airline.getFlights();
		for (Flight flight : lFlight) {
			findByFlight.addAll(airlineService.findByFlight(flight));
		}
		return findByFlight;

	}

}
