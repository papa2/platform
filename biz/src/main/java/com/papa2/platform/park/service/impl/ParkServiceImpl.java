package com.papa2.platform.park.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.papa2.platform.api.park.IParkService;
import com.papa2.platform.api.park.bo.Park;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.park.dao.IParkDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ParkServiceImpl implements IParkService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ParkServiceImpl.class);

	private IParkDao parkDao;

	@Override
	public Park getPark(String parkCode) {
		Park park = new Park();

		if (StringUtils.isBlank(parkCode)) {
			return null;
		}
		park.setParkCode(parkCode.trim());

		try {
			return parkDao.getPark(park);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(park), e);
		}

		return null;
	}

	@Override
	public List<Park> getParkStats() {
		try {
			return parkDao.getParkStats();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public IParkDao getParkDao() {
		return parkDao;
	}

	public void setParkDao(IParkDao parkDao) {
		this.parkDao = parkDao;
	}

}
