package com.papa2.platform.monitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.papa2.platform.api.cache.bo.CacheStats;
import com.papa2.platform.framework.dao.impl.BaseDaoImpl;
import com.papa2.platform.monitor.dao.ICacheMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorDaoImpl extends BaseDaoImpl implements ICacheMonitorDao {

	@Override
	public int getCacheMonitorCount(CacheStats cacheStats) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.cache.getCacheMonitorCount", cacheStats);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats) {
		return (List<CacheStats>) getSqlMapClientTemplate().queryForList("monitor.cache.getCacheMonitorList",
			cacheStats);
	}

	@Override
	public String createCacheMonitor(final List<CacheStats> cacheStatsList) {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<String>() {
			public String doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				StringBuilder sb = new StringBuilder();
				for (CacheStats s : cacheStatsList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("monitor.cache.createCacheMonitor", s));
				}

				executor.executeBatch();

				return sb.toString();
			}
		});
	}

}
