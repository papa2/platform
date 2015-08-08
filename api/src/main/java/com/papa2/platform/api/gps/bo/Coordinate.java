package com.papa2.platform.api.gps.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author xujiakun
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Coordinate implements Serializable {

	private static final long serialVersionUID = 8939720414431518138L;

	/**
	 * 经度.
	 */
	@XmlElement
	private double lon;

	/**
	 * 纬度.
	 */
	@XmlElement
	private double lat;

	private int offsetX;

	private int offsetY;

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

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

}
