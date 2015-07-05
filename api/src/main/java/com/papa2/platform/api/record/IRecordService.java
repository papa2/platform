package com.papa2.platform.api.record;

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
	BooleanResult record(String serialNo, String startTime, String endTime, String parkCardNo, String carNo);

}
