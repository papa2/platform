package com.papa2.platform.record.action;

import java.util.List;

import com.papa2.platform.api.record.IRecordService;
import com.papa2.platform.api.record.bo.Record;
import com.papa2.platform.framework.action.BaseAction;

/**
 * 
 * @author xujiakun
 * 
 */
public class RecordAction extends BaseAction {

	private static final long serialVersionUID = 3855666835571143684L;

	private IRecordService recordService;

	private int count;

	private List<Record> recordList;

	/**
	 * 已占用车位.
	 */
	private int occupy;

	/**
	 * 登录首页.
	 * 
	 * @return
	 */
	public String index() {
		Record record = new Record();
		record.setStart(0);
		record.setLimit(30);
		record.setSort("ID");
		record.setDir("DESC");

		count = recordService.getRecordCount(record);

		if (count > 0) {
			recordList = recordService.getRecordList(record);
		}

		record.setState("U");
		occupy = recordService.getRecordCount(record);

		return SUCCESS;
	}

	public IRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecordService recordService) {
		this.recordService = recordService;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public int getOccupy() {
		return occupy;
	}

	public void setOccupy(int occupy) {
		this.occupy = occupy;
	}

}
