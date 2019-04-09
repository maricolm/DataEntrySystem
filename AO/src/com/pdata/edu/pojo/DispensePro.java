package com.pdata.edu.pojo;

import java.util.Date;

/**
 * @author mulming
 * @time 2018年5月16日
 * @descript DispensePro
 */
public class DispensePro {
	private String dId;
	private String fId;
	private String pid;
	private String pName;
	private String sId;
	private String sName;
	private String cName;
	private String sDepartment;
	private int dpState;
	private int cState;
	private String eEname;//项目创建人姓名;
	private String pUrl;//模版下载地址
	private Date pDate;//项目创建时间
	private String backMsg;//驳回意见
	
	
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
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
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getsDepartment() {
		return sDepartment;
	}
	public void setsDepartment(String sDepartment) {
		this.sDepartment = sDepartment;
	}
	public int getDpState() {
		return dpState;
	}
	public void setDpState(int dpState) {
		this.dpState = dpState;
	}
	public int getcState() {
		return cState;
	}
	public void setcState(int cState) {
		this.cState = cState;
	}

}
