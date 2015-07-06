package com.papa2.platform.record.dao;

import java.util.List;

import com.papa2.platform.api.record.bo.Record;

public interface IRecordDao {

	/**
	 * 记录停车信息.
	 * 
	 * @param record
	 * @return
	 */
	String createRecord(Record record);

	/**
	 * 
	 * @param serialNo
	 * @param recordList
	 * @param modifyUser
	 * @return
	 */
	int createRecord(String serialNo, List<Record> recordList, String modifyUser);

}
