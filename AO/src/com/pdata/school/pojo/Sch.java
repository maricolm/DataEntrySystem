package com.pdata.school.pojo;

public class Sch {
	private String sId;
	private String sAccount;
	private String sPswd;
	private String sCid;
	private int sRole;
	private String sName;
	private String sDepartment;
	private String sUname;
	private String sPhone;
	private String sEmail;
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getsPswd() {
		return sPswd;
	}
	public void setsPswd(String sPswd) {
		this.sPswd = sPswd;
	}
	public String getsCid() {
		return sCid;
	}
	public void setsCid(String sCid) {
		this.sCid = sCid;
	}
	public int getsRole() {
		return sRole;
	}
	public void setsRole(int sRole) {
		this.sRole = sRole;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsDepartment() {
		return sDepartment;
	}
	public void setsDepartment(String sDepartment) {
		this.sDepartment = sDepartment;
	}
	public String getsUname() {
		return sUname;
	}
	public void setsUname(String sUname) {
		this.sUname = sUname;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	@Override
	public String toString() {
		return "Sch [sId=" + sId + ", sAccount=" + sAccount + ", sPswd=" + sPswd + ", sCid=" + sCid + ", sRole=" + sRole
				+ ", sName=" + sName + ", sDepartment=" + sDepartment + ", sUname=" + sUname + ", sPhone=" + sPhone
				+ ", sEmail=" + sEmail + "]";
	}

}
