package com.papa2.platform.monitor.dao;

import java.util.List;

import com.papa2.platform.api.monitor.bo.LogMonitor;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ILogMonitorDao {

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	int getLogMonitorCount(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitor
	 * @return
	 */
	List<LogMonitor> getLogMonitorList(LogMonitor logMonitor);

	/**
	 * 
	 * @param logMonitorList
	 * @return
	 */
	int createLogMonitor(List<LogMonitor> logMonitorList);

	/**
	 * 
	 * @return
	 */
	List<LogMonitor> getLogMonitorList4SendEmail();

}
