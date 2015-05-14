package com.papa2.platform.monitor.service.impl;

import java.util.List;

import com.papa2.platform.api.monitor.IActionMonitorService;
import com.papa2.platform.api.monitor.bo.ActionMonitor;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.monitor.dao.IActionMonitorDao;

/**
 * action log service.
 * 
 * @author xujiakun
 * 
 */
public class ActionMonitorServiceImpl implements IActionMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ActionMonitorServiceImpl.class);

	private IActionMonitorDao actionMonitorDao;

	@Override
	public boolean createActionMonitor(List<ActionMonitor> logs) {
		if (logs == null) {
			return false;
		}

		try {
			actionMonitorDao.createActionMonitor(logs);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logs), e);
		}

		return false;
	}

	@Override
	public int getActionMonitorCount(ActionMonitor log) {
		if (log == null) {
			return 0;
		}

		try {
			return actionMonitorDao.getActionMonitorCount(log);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(log), e);
		}

		return 0;
	}

	@Override
	public List<ActionMonitor> getActionMonitorList(ActionMonitor log) {
		if (log == null) {
			return null;
		}

		try {
			return actionMonitorDao.getActionMonitorList(log);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(log), e);
		}

		return null;
	}

	public IActionMonitorDao getActionMonitorDao() {
		return actionMonitorDao;
	}

	public void setActionMonitorDao(IActionMonitorDao actionMonitorDao) {
		this.actionMonitorDao = actionMonitorDao;
	}

}
