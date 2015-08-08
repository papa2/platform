package com.papa2.platform.gps.service.impl;

import org.apache.commons.lang.StringUtils;

import com.papa2.platform.api.gps.IGPSService;
import com.papa2.platform.api.gps.bo.Coordinate;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.ArithUtil;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.gps.dao.IGPSDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class GPSServiceImpl implements IGPSService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(GPSServiceImpl.class);

	private IGPSDao gpsDao;

	@Override
	public Coordinate adjust(String lon, String lat) {
		if (StringUtils.isBlank(lon) || StringUtils.isBlank(lat)) {
			return new Coordinate();
		}

		int zoom = 18;
		double on = 0;
		double at = 0;

		try {
			on = Double.parseDouble(lon);
			at = Double.parseDouble(lat);
		} catch (Exception e) {
			logger.error("lon:" + lon + ";lat:" + lat, e);
		}

		Coordinate coordinate = getOffset(on, at);
		if (coordinate == null) {
			return new Coordinate();
		}

		coordinate.setLon(pixelToLon(lonToPixel(on, zoom) + coordinate.getOffsetY(), zoom));
		coordinate.setLat(pixelToLat(latToPixel(at, zoom) + coordinate.getOffsetX(), zoom));

		return coordinate;
	}

	/**
	 * 
	 * @param lon
	 * @param lat
	 * @return
	 */
	private Coordinate getOffset(double lon, double lat) {
		Coordinate coordinate = new Coordinate();

		coordinate.setLon(ArithUtil.round(lon * 100, 0));
		coordinate.setLat(ArithUtil.round(lat * 100, 0));

		try {
			return gpsDao.getCoordinate(coordinate);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(coordinate), e);
		}

		return null;
	}

	private double lonToPixel(double lon, int zoom) {
		return (lon + 180) * (256L << zoom) / 360;
	}

	private double pixelToLon(double pixelX, int zoom) {
		return pixelX * 360 / (256L << zoom) - 180;
	}

	private double latToPixel(double lat, int zoom) {
		double siny = Math.sin(lat * Math.PI / 180);
		double y = Math.log((1 + siny) / (1 - siny));
		return (128 << zoom) * (1 - y / (2 * Math.PI));
	}

	private double pixelToLat(double pixelY, int zoom) {
		double y = 2 * Math.PI * (1 - pixelY / (128 << zoom));
		double z = Math.pow(Math.E, y);
		double siny = (z - 1) / (z + 1);
		return Math.asin(siny) * 180 / Math.PI;
	}

	public IGPSDao getGpsDao() {
		return gpsDao;
	}

	public void setGpsDao(IGPSDao gpsDao) {
		this.gpsDao = gpsDao;
	}

}
