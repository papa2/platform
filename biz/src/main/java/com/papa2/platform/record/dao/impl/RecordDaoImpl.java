package com.papa2.platform.record.dao.impl;

import java.sql.SQLException;
import java.util.List;

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
	public String createRecord(Record record) {
		return (String) getSqlMapClientTemplate().insert("record.createRecord", record);
	}

	@Override
	public int createRecord(final String serialNo, final List<Record> recordList, final String modifyUser) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Record record : recordList) {
					record.setParkId(serialNo);
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
