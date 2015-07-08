package com.papa2.platform.record.service.impl;

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
	public BooleanResult record(String serialNo, String startTime, String endTime, String parkCardNo, String carNo) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Record record = new Record();

		if (StringUtils.isBlank(serialNo)) {
			result.setCode("终端序列号不能为空。");
			return result;
		}

		record.setParkId(serialNo.trim());

		if (StringUtils.isBlank(startTime)) {
			result.setCode("停车开始时间不能为空。");
			return result;
		}

		record.setStartTime(startTime.trim());
		record.setEndTime(endTime);

		if (StringUtils.isBlank(parkCardNo) && StringUtils.isBlank(carNo)) {
			result.setCode("停车卡和车牌不能同时为空。");
			return result;
		}

		record.setParkCardId(parkCardNo);
		record.setCarNo(carNo);

		record.setModifyUser(serialNo);

		try {
			result.setCode(recordDao.createRecord(record));
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return result;
	}

	@Override
	public BooleanResult record(String serialNo, String recordList) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(serialNo)) {
			result.setCode("终端序列号不能为空。");
			return result;
		}

		if (StringUtils.isBlank(recordList)) {
			result.setCode("停车信息不能为空。");
			return result;
		}

		try {
			result.setCode(String.valueOf(recordDao.createRecord(serialNo.trim(),
				JSON.parseArray(recordList, Record.class), serialNo)));
			result.setResult(true);
		} catch (Exception e) {
			logger.error("serialNo:" + serialNo + LogUtil.parserBean(recordList), e);
		}

		return result;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
