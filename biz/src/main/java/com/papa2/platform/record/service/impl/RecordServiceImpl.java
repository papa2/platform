package com.papa2.platform.record.service.impl;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;

import com.alibaba.fastjson.JSON;
import com.papa2.platform.api.park.IParkService;
import com.papa2.platform.api.park.bo.Park;
import com.papa2.platform.api.record.IRecordService;
import com.papa2.platform.api.record.bo.Record;
import com.papa2.platform.framework.bo.BooleanResult;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
import com.papa2.platform.framework.util.EncryptUtil;
import com.papa2.platform.framework.util.LogUtil;
import com.papa2.platform.record.dao.IRecordDao;

/**
 * 对外提供接口 记录停车信息.
 * 
 * @author xujiakun
 * 
 */
public class RecordServiceImpl implements IRecordService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(RecordServiceImpl.class);

	@Context
	private MessageContext context;

	private IParkService parkService;

	private IRecordDao recordDao;

	private BooleanResult validate(String parkCode, String timestamp, String signature) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(parkCode)) {
			result.setCode("停车场编号不能为空。");
			return result;
		}

		if (StringUtils.isBlank(timestamp)) {
			result.setCode("时间戳不能为空。");
			return result;
		}

		if (StringUtils.isBlank(signature)) {
			result.setCode("签名不能为空。");
			return result;
		}

		Park park = parkService.getPark(parkCode.trim());

		if (park == null) {
			result.setCode("停车场信息不存在。");
			return result;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("parkCode=").append(parkCode.trim()).append("&parkSecret=").append(park.getParkSecret())
			.append("&timestamp=").append(timestamp.trim());

		String sign = null;
		try {
			sign = EncryptUtil.encryptSHA(sb.toString());
		} catch (IOException e) {
			logger.error(e);

			result.setCode("加密失败。");
			return result;
		}

		if (!sign.equals(signature)) {
			result.setCode("签名验证失败。");
			return result;
		}

		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult record(String parkCode, String timestamp, String signature, String startTime, String endTime,
		String cardNo, String carNo) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Record record = new Record();

		if (StringUtils.isBlank(parkCode)) {
			result.setCode("停车场编号不能为空。");
			return result;
		}

		record.setParkCode(parkCode.trim());

		result = validate(parkCode, timestamp, signature);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			result.setCode("停车开始时间和结束时间不能同时为空。");
			return result;
		}

		record.setStartTime(startTime);
		record.setEndTime(endTime);

		if (StringUtils.isBlank(cardNo) && StringUtils.isBlank(carNo)) {
			result.setCode("停车卡和车牌不能同时为空。");
			return result;
		}

		record.setCardNo(cardNo);
		record.setCarNo(carNo);
		record.setIp(context.getHttpServletRequest().getRemoteAddr());

		record.setModifyUser(parkCode);

		if (StringUtils.isNotBlank(startTime)) {
			int count = getRecordCount(record);
			if (count != 0) {
				result.setCode("停车记录已存在。");
				return result;
			}

			try {
				result.setCode(String.valueOf(recordDao.createRecord(record)));
				result.setResult(true);
			} catch (Exception e) {
				logger.error(LogUtil.parserBean(record), e);

				result.setCode("记录停车信息失败。");
			}

			return result;
		}

		if (StringUtils.isNotBlank(endTime)) {
			try {
				int count = recordDao.updateRecord(record);
				if (count > 0) {
					result.setCode("更新停车信息成功。");
					result.setResult(true);
				}
			} catch (Exception e) {
				logger.error(LogUtil.parserBean(record), e);

				result.setCode("更新停车信息失败。");
			}

			return result;
		}

		return result;
	}

	@Override
	public BooleanResult record(String parkCode, String timestamp, String signature, String recordList) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(recordList)) {
			result.setCode("停车信息不能为空。");
			return result;
		}

		List<Record> records = null;
		try {
			records = JSON.parseArray(recordList, Record.class);
		} catch (Exception e) {
			logger.error(recordList, e);

			result.setCode("停车信息反序列化失败。");
			return result;
		}

		if (records == null || records.size() == 0) {
			result.setCode("停车信息不能为空。");
			return result;
		}

		result.setResult(true);

		BooleanResult res = null;
		int count = 0;
		for (Record record : records) {
			res =
				record(parkCode, timestamp, signature, record.getStartTime(), record.getEndTime(), record.getCardNo(),
					record.getCarNo());
			if (res.getResult()) {
				count++;
			}
		}

		result.setCode("停车信息，成功同步 " + count + "；" + "失败 " + (records.size() - count) + "。");
		return result;
	}

	private int getRecordCount(Record record) {
		try {
			return recordDao.getRecordCount(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return -1;
	}

	public MessageContext getContext() {
		return context;
	}

	public void setContext(MessageContext context) {
		this.context = context;
	}

	public IParkService getParkService() {
		return parkService;
	}

	public void setParkService(IParkService parkService) {
		this.parkService = parkService;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
