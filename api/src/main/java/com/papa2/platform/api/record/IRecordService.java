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
	 * @param startTime
	 * @param endTime
	 * @param cardNo
	 * @param carNo
	 * @param timestamp
	 * @param signature
	 * @return
	 */
	@POST
	@Path("/record")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("parkCode") String parkCode, @FormParam("startTime") String startTime,
		@FormParam("endTime") String endTime, @FormParam("cardNo") String cardNo,
		@FormParam("carNo") String carNo, @FormParam("timestamp") String timestamp,
		@FormParam("signature") String signature);

	/**
	 * 
	 * @param parkCode
	 * @param recordList
	 * @param timestamp
	 * @param signature
	 * @return
	 */
	@POST
	@Path("/recordAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("parkCode") String parkCode, @FormParam("recordList") String recordList,
		@FormParam("timestamp") String timestamp, @FormParam("signature") String signature);

}
