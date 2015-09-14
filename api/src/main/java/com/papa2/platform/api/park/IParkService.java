package com.papa2.platform.api.park;

import java.util.List;

import com.papa2.platform.api.park.bo.Park;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IParkService {

	/**
	 * 
	 * @param parkCode
	 * @return
	 */
	Park getPark(String parkCode);

	/**
	 * 
	 * @return
	 */
	List<Park> getParkStats();

}
