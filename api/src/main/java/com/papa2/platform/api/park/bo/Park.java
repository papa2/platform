package com.papa2.platform.api.park.bo;

public class Park {

	private Long parkId;

	private String parkCode;

	private String parkSecret;

	private String parkName;

	private String address;

	private double lon;

	private double lat;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	private int occupy;

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getParkSecret() {
		return parkSecret;
	}

	public void setParkSecret(String parkSecret) {
		this.parkSecret = parkSecret;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public int getOccupy() {
		return occupy;
	}

	public void setOccupy(int occupy) {
		this.occupy = occupy;
	}

}
