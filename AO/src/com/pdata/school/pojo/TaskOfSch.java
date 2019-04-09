package com.pdata.school.pojo;

import java.util.Date;

/**
 * @author mulming
 * @time 2018年5月24日
 * @descript TakOfSch
 */
public class TaskOfSch {
	private String tId;
	private String fId;
	private String pId;
	private String pName;
	private String cId;
	private String cName;
	private String tDepartment;
	private int tPState;
	private int tState;
	private String eEname;
	private String pUrl;
	private Date pDate;
	private String backMsg;
	
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String gettDepartment() {
		return tDepartment;
	}
	public void settDepartment(String tDepartment) {
		this.tDepartment = tDepartment;
	}
	public int gettPState() {
		return tPState;
	}
	public void settPState(int tPState) {
		this.tPState = tPState;
	}
	public int gettState() {
		return tState;
	}
	public void settState(int tState) {
		this.tState = tState;
	}
	public String geteEname() {
		return eEname;
	}
	public void seteEname(String eEname) {
		this.eEname = eEname;
	}
	public String getpUrl() {
		return pUrl;
	}
	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getBackMsg() {
		return backMsg;
	}
	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}
	@Override
	public String toString() {
		return "TakOfSch [tId=" + tId + ", pId=" + pId + ", pName=" + pName + ", cId=" + cId + ", cName=" + cName
				+ ", tDepartment=" + tDepartment + ", tPState=" + tPState + ", tState=" + tState + ", eEname=" + eEname
				+ ", pUrl=" + pUrl + ", pDate=" + pDate + ", backMsg=" + backMsg + "]";
	}

}
