package nl.hu.v1wac.firstapp.webservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaolmpl;
import nl.hu.v1wac.firstapp.services.WorldService;

@Path("/countries")
public class ServiceProvider {
	private static WorldService worldService = new WorldService();
	

	@GET
	@RolesAllowed("user")
	public static WorldService getWorldService() {
		return worldService;
	}

	
	@PUT
	@RolesAllowed("admin")
	@Path("{code}")
	public Response update(@PathParam("code") String code, @FormParam("name") String naam, @FormParam("capital") String hoofdstad,
			@FormParam("surface") int oppervlakte, @FormParam("population") int mensen) {
		CountryPostgresDaolmpl db = new CountryPostgresDaolmpl();
		Country c = new Country(); 
		c.setCode(code);
		c.setName(naam);
		c.setCapital(hoofdstad);
		c.setPopulation(mensen);
		c.setSurface(oppervlakte);
		boolean r = db.update(c);
		
		if (!r) {
			return Response.status(404).build();
		}
		
		return Response.ok().build();
	}
	
	@POST
	@RolesAllowed("admin")
	public Response save(@FormParam("code") String coden, @FormParam("name") String naam, @FormParam("capital") String hoofdstad) {
		CountryPostgresDaolmpl db = new CountryPostgresDaolmpl();
		Country c = new Country();
		c.setCode(coden);
		c.setName(naam);
		c.setCapital(hoofdstad);
		boolean resp = db.save(c);
		
		if (!resp) {
			return Response.status(402).build();
		}
		
		return Response.ok().build();
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	public Response delete(@PathParam("code") String code) {
		CountryPostgresDaolmpl db = new CountryPostgresDaolmpl();
		Country c = new Country();
		c.setCode(code);
		db.delete(c);
		return Response.ok().build();
	}
}