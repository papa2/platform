package com.papa2.platform.api.gps;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.papa2.platform.api.gps.bo.Coordinate;

public interface IGPSService {

	/**
	 * 
	 * @param lon
	 * @param lat
	 * @return
	 */
	@GET
	@Path("/vp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	Coordinate adjust(@FormParam("lon") String lon, @FormParam("lat") String lat);

}
