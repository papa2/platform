package com.papa2.platform.api.monitor.bo;

import com.papa2.platform.framework.bo.SearchInfo;

/**
 * log4j.
 * 
 * @author xujiakun
 * 
 */
public class LogMonitor extends SearchInfo {

	private static final long serialVersionUID = 3856860374996367071L;

	private String id;

	private String className;

	private String methodName;

	private String lineNumber;

	private String message;

	private String e;

	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
