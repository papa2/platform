package com.papa2.platform.park.dao.impl;

import com.papa2.platform.api.park.bo.Park;
import com.papa2.platform.framework.dao.impl.BaseDaoImpl;
import com.papa2.platform.park.dao.IParkDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ParkDaoImpl extends BaseDaoImpl implements IParkDao {

	@Override
	public Park getPark(Park park) {
		return (Park) getSqlMapClientTemplate().queryForObject("park.getPark", park);
	}

}
