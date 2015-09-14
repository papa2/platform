package com.papa2.platform.park.dao;

import java.util.List;

import com.papa2.platform.api.park.bo.Park;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IParkDao {

	/**
	 * 
	 * @param park
	 * @return
	 */
	Park getPark(Park park);

	/**
	 * 
	 * @return
	 */
	List<Park> getParkStats();

}
