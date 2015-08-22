package com.papa2.platform.record.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.papa2.platform.api.record.IRecordService;
import com.papa2.platform.api.record.bo.Record;
import com.papa2.platform.framework.bo.BooleanResult;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
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

	private IRecordDao recordDao;

	@Override
	public BooleanResult record(String parkCode, String startTime, String endTime, String cardNo, String carNo,
		String timestamp, String signature) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Record record = new Record();

		if (StringUtils.isBlank(parkCode)) {
			result.setCode("终端序列号不能为空。");
			return result;
		}

		record.setParkCode(parkCode.trim());

		if (StringUtils.isBlank(startTime)) {
			result.setCode("停车开始时间不能为空。");
			return result;
		}

		record.setStartTime(startTime.trim());
		record.setEndTime(endTime);

		if (StringUtils.isBlank(cardNo) && StringUtils.isBlank(carNo)) {
			result.setCode("停车卡和车牌不能同时为空。");
			return result;
		}

		record.setCardNo(cardNo);
		record.setCarNo(carNo);

		record.setModifyUser(parkCode);

		try {
			int count = recordDao.updateRecord(record);
			if (count > 0) {
				result.setCode("更新停车信息成功。");
				result.setResult(true);
				return result;
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			result.setCode("更新停车信息失败。");
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

	@Override
	public BooleanResult record(String parkCode, String recordList, String timestamp, String signature) {
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
				record(parkCode, record.getStartTime(), record.getEndTime(), record.getCardNo(), record.getCarNo(),
					timestamp, signature);
			if (res.getResult()) {
				count++;
			}
		}

		result.setCode("停车信息，成功同步 " + count + "；" + "失败 " + (records.size() - count) + "。");
		return result;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
