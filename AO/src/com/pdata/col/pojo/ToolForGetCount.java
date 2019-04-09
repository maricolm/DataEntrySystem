package com.pdata.col.pojo;

/**
 * @author mulming
 * @time 2018年7月19日
 * @descript ToolForGetCount
 */
public class ToolForGetCount {
	private String table;
	private String cId;
	private String sId;
	
	/**
	 * @param table
	 * @param cId
	 * @param sId
	 */
	public ToolForGetCount(String table,String sId,String cId) {
		super();
		this.table = table;
		this.cId = cId;
		this.sId = sId;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public ToolForGetCount(String table, String cId) {
		super();
		this.table = table;
		this.cId = cId;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}

}
