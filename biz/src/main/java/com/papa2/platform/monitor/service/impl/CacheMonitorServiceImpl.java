package com.papa2.platform.monitor.service.impl;

import java.util.List;

import com.papa2.platform.api.cache.bo.CacheStats;
import com.papa2.platform.api.monitor.ICacheMonitorService;
import com.papa2.platform.framework.bo.BooleanResult;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.monitor.dao.ICacheMonitorDao;

/**
 * cache monitor service.
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorServiceImpl implements ICacheMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CacheMonitorServiceImpl.class);

	private ICacheMonitorDao cacheMonitorDao;

	@Override
	public int getCacheMonitorCount(CacheStats cacheStats) {
		if (cacheStats == null) {
			return 0;
		}

		try {
			return cacheMonitorDao.getCacheMonitorCount(cacheStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cacheStats), e);
		}

		return 0;
	}

	@Override
	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats) {
		if (cacheStats == null) {
			return null;
		}

		try {
			return cacheMonitorDao.getCacheMonitorList(cacheStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cacheStats), e);
		}

		return null;
	}

	@Override
	public BooleanResult createCacheMonitor(List<CacheStats> cacheStatsList) {
		BooleanResult res = new BooleanResult();
		res.setResult(false);

		if (cacheStatsList == null || cacheStatsList.size() == 0) {
			res.setCode("cache 信息不能为空！");
			return res;
		}

		try {
			String result = cacheMonitorDao.createCacheMonitor(cacheStatsList);
			res.setCode(result);
			res.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cacheStatsList), e);

			res.setCode(ICacheMonitorService.ERROR_MESSAGE);
		}

		return res;
	}

	public ICacheMonitorDao getCacheMonitorDao() {
		return cacheMonitorDao;
	}

	public void setCacheMonitorDao(ICacheMonitorDao cacheMonitorDao) {
		this.cacheMonitorDao = cacheMonitorDao;
	}

}
