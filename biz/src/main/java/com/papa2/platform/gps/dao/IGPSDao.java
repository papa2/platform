package com.papa2.platform.gps.dao;

import com.papa2.platform.api.gps.bo.Coordinate;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IGPSDao {

	/**
	 * 
	 * @param coordinate
	 * @return
	 */
	Coordinate getCoordinate(Coordinate coordinate);

}
