package com.papa2.platform.api.park;

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

}
