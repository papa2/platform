package com.papa2.platform.record.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.papa2.platform.api.park.IParkService;
import com.papa2.platform.api.park.bo.Park;
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

	private IParkService parkService;

	private IRecordService recordService;

	private String parkList;

	private String parkCode;

	private int count;

	private List<Record> recordList;

	/**
	 * 登录首页.
	 * 
	 * @return
	 */
	public String index() {
		List<Park> list = parkService.getParkStats();
		if (list != null && list.size() > 0) {
			parkList = JSON.toJSONString(list);
		}

		return SUCCESS;
	}

	public String detail() {
		Record record = new Record();

		if (StringUtils.isNotBlank(parkCode)) {
			record.setParkCode(parkCode.trim());
		}

		record.setStart(0);
		record.setLimit(50);
		record.setSort("MODIFY_DATE");
		record.setDir("DESC");

		count = recordService.getRecordCount(record);

		if (count > 0) {
			recordList = recordService.getRecordList(record);
		}

		return SUCCESS;
	}

	public IParkService getParkService() {
		return parkService;
	}

	public void setParkService(IParkService parkService) {
		this.parkService = parkService;
	}

	public IRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecordService recordService) {
		this.recordService = recordService;
	}

	public String getParkList() {
		return parkList;
	}

	public void setParkList(String parkList) {
		this.parkList = parkList;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
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

}
