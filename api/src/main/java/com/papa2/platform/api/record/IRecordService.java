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
	 * @param serialNo
	 *            cube序列号.
	 * @param startTime
	 * @param endTime
	 * @param parkCardNo
	 * @param carNo
	 * @return
	 */
	@POST
	@Path("/record")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("serialNo") String serialNo, @FormParam("startTime") String startTime,
		@FormParam("endTime") String endTime, @FormParam("parkCardNo") String parkCardNo,
		@FormParam("carNo") String carNo);

	/**
	 * 
	 * @param serialNo
	 *            cube序列号.
	 * @param recordList
	 * @return
	 */
	@POST
	@Path("/recordAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	BooleanResult record(@FormParam("serialNo") String serialNo, @FormParam("recordList") String recordList);

}
