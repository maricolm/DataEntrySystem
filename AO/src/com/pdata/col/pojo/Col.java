package com.pdata.col.pojo;
/**
 *mulming
 *下午10:20:57
 */
public class Col {
	private String cId;
	private String cAccount;
	private String cPswd;
	private String cCid;
	private int cRole;
	private String cName;
	private String cDepartment;
	private String cUname;
	private String cPhone;
	private String cEmail;
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcAccount() {
		return cAccount;
	}
	public void setcAccount(String cAccount) {
		this.cAccount = cAccount;
	}
	public String getcPswd() {
		return cPswd;
	}
	public void setcPswd(String cPswd) {
		this.cPswd = cPswd;
	}
	public String getcCid() {
		return cCid;
	}
	public void setcCid(String cCid) {
		this.cCid = cCid;
	}
	public int getcRole() {
		return cRole;
	}
	public void setcRole(int cRole) {
		this.cRole = cRole;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcDepartment() {
		return cDepartment;
	}
	public void setcDepartment(String cDepartment) {
		this.cDepartment = cDepartment;
	}
	public String getcUname() {
		return cUname;
	}
	public void setcUname(String cUname) {
		this.cUname = cUname;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	@Override
	public String toString() {
		return "Col [cId=" + cId + ", cAccount=" + cAccount + ", cPswd=" + cPswd + ", cCid=" + cCid + ", cRole=" + cRole
				+ ", cName=" + cName + ", cDepartment=" + cDepartment + ", cUname=" + cUname + ", cPhone=" + cPhone
				+ ", cEmail=" + cEmail + "]";
	}
	
}
