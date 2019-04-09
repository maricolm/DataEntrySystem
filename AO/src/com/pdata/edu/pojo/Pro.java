package com.pdata.edu.pojo;

import java.util.Date;

public class Pro {
	private String pId;
	private String pName;
	private String pAlias;
	private String pEid;
	private String pEname;
	private int pState;
	private String pUrl;
	private Date pDate;
	public String getpEname() {
		return pEname;
	}
	public void setpEname(String pEname) {
		this.pEname = pEname;
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
	public String getpAlias() {
		return pAlias;
	}
	public void setpAlias(String pAlias) {
		this.pAlias = pAlias;
	}
	public String getpEid() {
		return pEid;
	}
	public void setpEid(String pEid) {
		this.pEid = pEid;
	}
	public int getpState() {
		return pState;
	}
	public void setpState(int pState) {
		this.pState = pState;
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
	@Override
	public String toString() {
		return "Pro [pId=" + pId + ", pName=" + pName + ", pAlias=" + pAlias + ", pEid=" + pEid + ", pState=" + pState
				+ ", pUrl=" + pUrl + ", pDate=" + pDate + "]";
	}

}
