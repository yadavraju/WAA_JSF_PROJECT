package edu.mum.cs545.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
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
import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;

@Named
@Path("airplane")
public class AirPlaneController {
	
	@Inject
	AirplaneService airplaneeService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Airplane airplane) {
		try {
			airplaneeService.create(airplane);
			return Response.ok(airplane).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot create airplane.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response update(Airplane airplane) {
		try {
			airplaneeService.update(airplane);
			return Response.ok(airplane).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot update airplane.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete")
	public Response delete(Airplane airplane) {
		try {
			airplaneeService.delete(airplane);
			return Response.ok(airplane).build();
		} catch (Exception e) {
			return Response.serverError().entity("Cannot delete airplane.").build();
		}
	}

	
	@Path("flight/model/{name}")
	@GET
	public List<Airplane> getAirPlaneByModel(@PathParam("name")String model) {
		return airplaneeService.findByModel(model);
	}

	@Path("list")
	@GET
	public List<Airplane> getAirlineListOfFlight() {
		List<Airplane> result = new ArrayList<>();
		if (airplaneeService.findAll() != null) {
			result = airplaneeService.findAll();
		}
		return result;
	}

}
