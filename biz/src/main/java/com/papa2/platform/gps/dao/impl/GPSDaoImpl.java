package com.papa2.platform.gps.dao.impl;

import com.papa2.platform.api.gps.bo.Coordinate;
import com.papa2.platform.framework.dao.impl.BaseDaoImpl;
import com.papa2.platform.gps.dao.IGPSDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class GPSDaoImpl extends BaseDaoImpl implements IGPSDao {

	@Override
	public Coordinate getCoordinate(Coordinate coordinate) {
		return (Coordinate) getSqlMapClientTemplate().queryForObject("gps.getCoordinate", coordinate);
	}

}
