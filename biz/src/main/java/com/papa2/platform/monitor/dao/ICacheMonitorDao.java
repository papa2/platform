package com.papa2.platform.monitor.dao;

import java.util.List;

import com.papa2.platform.api.cache.bo.CacheStats;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICacheMonitorDao {

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	int getCacheMonitorCount(CacheStats cacheStats);

	/**
	 * 
	 * @param cacheStats
	 * @return
	 */
	List<CacheStats> getCacheMonitorList(CacheStats cacheStats);

	/**
	 * 
	 * @param cacheStatsList
	 * @return
	 */
	String createCacheMonitor(List<CacheStats> cacheStatsList);

}
