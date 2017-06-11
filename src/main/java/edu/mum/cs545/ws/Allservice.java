package edu.mum.cs545.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.model.FlightQuery;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;
import edu.mum.gf.workaround.Utills;

@Named
@ViewScoped
public class Allservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineService;
	@Inject
	private AirportService airportService;

	private FlightQuery flightQuery;

	// date-time, airline, departure, and destination

	private List<Flight> listFlight;

	@PostConstruct
	public void findAllFlight() {
		flightQuery = new FlightQuery();
		listFlight = flightService.findAll();
	}

	public void SearchFlight() {
		if (flightQuery == null
				|| ("".equals(flightQuery.getAirlineName()) && "".equals(flightQuery.getOriginAirportCode())
						&& "".equals(flightQuery.getDestinationAirportCode()))) {
			listFlight = flightService.findAll();
		} else {

			if (flightQuery.getAirlineName() != null && !"".equals(flightQuery.getAirlineName())) {
				Airline airlineNew = airlineService.findByName(flightQuery.getAirlineName());
				if (airlineNew != null) {
					listFlight = flightService.findByAirline(airlineNew);
				}
			}

			if (flightQuery.getOriginAirportCode() != null && !"".equals(flightQuery.getOriginAirportCode())) {
				Airport airportNew = airportService.findByCode(flightQuery.getOriginAirportCode());
				if (airportNew != null) {
					if (listFlight != null) {
						List<Flight> listFlight1 = flightService.findByOrigin(airportNew);
						List<Flight> listFlight2 = new ArrayList<Flight>();
						for (Flight flight : listFlight1) {
							if (listFlight.contains(flight)) {
								listFlight2.add(flight);
							}
						}
						listFlight = new ArrayList<Flight>(listFlight2);
					} else {
						listFlight = flightService.findByOrigin(airportNew);
					}
				}
			}

			if (flightQuery.getDestinationAirportCode() != null
					&& !"".equals(flightQuery.getDestinationAirportCode())) {
				Airport airportNew = airportService.findByCode(flightQuery.getDestinationAirportCode());
				if (airportNew != null) {
					if (listFlight != null) {
						List<Flight> listFlight1 = flightService.findByDestination(airportNew);
						List<Flight> listFlight2 = new ArrayList<Flight>();
						for (Flight flight : listFlight1) {
							if (listFlight.contains(flight)) {
								listFlight2.add(flight);
							}
						}
						listFlight = new ArrayList<Flight>(listFlight2);
					} else {
						listFlight = flightService.findByDestination(airportNew);
					}
				}
			}

		}
	}

	public void createFromJSF() {
		Airline a = new Airline();
		a.setName(flightQuery.getAirlineName());
		airlineService.create(a);

	}
	
	public void deleteFromJSF(Airline airline) {
		airlineService.delete(airline);

	}
	
	public void updateFromJSF(Airline airline) {
		String s = Utills.getRandomString()+"_AirLine";
		airline.setName(s);
		airlineService.update(airline);

	}

	public List<Flight> getListFlight() {
		return listFlight;
	}

	public void setListFlight(List<Flight> listFlight) {
		this.listFlight = listFlight;
	}

	public FlightQuery getFlightQuery() {
		return flightQuery;
	}

	public void setFlightQuery(FlightQuery flightQuery) {
		this.flightQuery = flightQuery;
	}

}
