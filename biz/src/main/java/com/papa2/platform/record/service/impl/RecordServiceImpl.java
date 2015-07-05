package com.papa2.platform.record.service.impl;

import com.papa2.platform.api.record.IRecordService;
import com.papa2.platform.framework.bo.BooleanResult;
import com.papa2.platform.framework.log.Logger4jCollection;
import com.papa2.platform.framework.log.Logger4jExtend;
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
		// TODO Auto-generated method stub
		return null;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
