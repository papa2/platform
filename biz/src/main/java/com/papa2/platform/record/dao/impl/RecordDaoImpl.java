package com.papa2.platform.record.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.papa2.platform.api.record.bo.Record;
import com.papa2.platform.framework.dao.impl.BaseDaoImpl;
import com.papa2.platform.record.dao.IRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class RecordDaoImpl extends BaseDaoImpl implements IRecordDao {

	@Override
	public int getRecordCount(String parkCode, String cardNo, String carNo, String startTime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("parkCode", parkCode);
		map.put("cardNo", cardNo);
		map.put("carNo", carNo);
		map.put("startTime", startTime);
		return (Integer) getSqlMapClientTemplate().queryForObject("record.getRecordCount1", map);
	}

	@Override
	public int getRecordCount(Record record) {
		return (Integer) getSqlMapClientTemplate().queryForObject("record.getRecordCount2", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getRecordList(Record record) {
		return getSqlMapClientTemplate().queryForList("record.getRecordList", record);
	}

	@Override
	public int updateRecord(Record record) {
		return getSqlMapClientTemplate().update("record.updateRecord", record);
	}

	@Override
	public Long createRecord(Record record) {
		return (Long) getSqlMapClientTemplate().insert("record.createRecord", record);
	}

	@Override
	public int createRecord(final String parkCode, final List<Record> recordList, final String modifyUser) {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Record record : recordList) {
					record.setParkCode(parkCode);
					record.setModifyUser(modifyUser);
					executor.insert("record.createRecord", record);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

}
