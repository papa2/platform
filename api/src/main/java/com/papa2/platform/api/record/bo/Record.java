package com.papa2.platform.api.record.bo;

import com.papa2.platform.framework.bo.SearchInfo;

/**
 * 停车记录.
 * 
 * @author xujiakun
 * 
 */
public class Record extends SearchInfo {

	private static final long serialVersionUID = 5810036235970390092L;

	private Long id;

	private String parkCode;

	/**
	 * 停车开始时间.
	 */
	private String startTime;

	/**
	 * 停车结束时间.
	 */
	private String endTime;

	/**
	 * 停车卡信息.
	 */
	private String cardNo;

	/**
	 * 车牌信息.
	 */
	private String carNo;

	private String ip;

	private String state;

	private String modifyUser;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
