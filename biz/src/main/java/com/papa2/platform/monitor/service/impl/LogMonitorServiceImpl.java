package com.papa2.platform.monitor.service.impl;

import java.util.List;

import com.papa2.platform.api.monitor.ILogMonitorService;
import com.papa2.platform.api.monitor.bo.LogMonitor;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.monitor.dao.ILogMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorServiceImpl implements ILogMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(LogMonitorServiceImpl.class);

	private ILogMonitorDao logMonitorDao;

	@Override
	public int getLogMonitorCount(LogMonitor logMonitor) {
		if (logMonitor == null) {
			return 0;
		}

		try {
			return logMonitorDao.getLogMonitorCount(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return 0;
	}

	@Override
	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor) {
		if (logMonitor == null) {
			return null;
		}

		try {
			return logMonitorDao.getLogMonitorList(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return null;
	}

	@Override
	public boolean createLogMonitor(List<LogMonitor> logMonitorList) {
		if (logMonitorList == null) {
			return false;
		}

		try {
			logMonitorDao.createLogMonitor(logMonitorList);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitorList), e);
		}

		return false;
	}

	@Override
	public List<LogMonitor> getLogMonitorList4SendEmail() {
		try {
			return logMonitorDao.getLogMonitorList4SendEmail();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ILogMonitorDao getLogMonitorDao() {
		return logMonitorDao;
	}

	public void setLogMonitorDao(ILogMonitorDao logMonitorDao) {
		this.logMonitorDao = logMonitorDao;
	}

}
