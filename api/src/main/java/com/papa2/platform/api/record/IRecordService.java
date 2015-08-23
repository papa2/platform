package com.papa2.platform.api.record;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.papa2.platform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IRecordService {

	/**
	 * 
	 * @param parkCode
	 * @param timestamp
	 * @param signature
	 * @param startTime
	 * @param endTime
	 * @param cardNo
	 * @param carNo
	 * @return
	 */
	@POST
	@Path("/record")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("parkCode") String parkCode, @FormParam("timestamp") String timestamp,
		@FormParam("signature") String signature, @FormParam("startTime") String startTime,
		@FormParam("endTime") String endTime, @FormParam("cardNo") String cardNo, @FormParam("carNo") String carNo);

	/**
	 * 
	 * @param parkCode
	 * @param timestamp
	 * @param signature
	 * @param recordList
	 * @return
	 */
	@POST
	@Path("/recordAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("parkCode") String parkCode, @FormParam("timestamp") String timestamp,
		@FormParam("signature") String signature, @FormParam("recordList") String recordList);

}
