package edu.mum.cs545.ws;

import java.util.ArrayList;
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

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;

@Path("airport")
public class AirPortController {
	@Inject
	private AirportService airporteService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response find(@PathParam("id") int id) {
		Airport airport = new Airport();
		airport.setId(id);
		airport = airporteService.find(airport);

		if (airport == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("Airport with id %d doesn't exist.", id)).build();
		}

		return Response.ok(airport).build();
	}

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Airport airline) {
		try {
			airporteService.create(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot create airline.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response update(Airport airline) {
		try {
			airporteService.update(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot update airline.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete")
	public Response delete(Airport airline) {
		try {
			airporteService.delete(airline);
			return Response.ok(airline).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot delete airline.").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> index() {
		return airporteService.findAll();
	}

	@Path("flight/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> getAirportbyFlight(@PathParam("name") String name) { 
		return airporteService.findByName(name);

	}

}