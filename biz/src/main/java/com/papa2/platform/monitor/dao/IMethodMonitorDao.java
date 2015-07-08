package com.papa2.platform.monitor.dao;

import java.util.List;

import com.papa2.platform.api.monitor.bo.MethodMonitor;

/**
 * method monitor dao.
 * 
 * @author xujiakun
 * 
 */
public interface IMethodMonitorDao {

	/**
	 * 
	 * @param methodMonitor
	 * @return
	 */
	int getMethodMonitorCount(MethodMonitor methodMonitor);

	/**
	 * 
	 * @param methodMonitor
	 * @return
	 */
	List<MethodMonitor> getMethodMonitorList(MethodMonitor methodMonitor);

	/**
	 * 创建methodMonitor.
	 * 
	 * @param methodMonitor
	 * @return
	 */
	Long createMethodMonitor(MethodMonitor methodMonitor);

}
