package com.pdata.edu.pojo;

public class Edu {
	String eId;
	String eAccount;
	String ePswd;
	int eRole;
	String eName;
	String eDepartment;
	String ePhone;
	String eEmail;
	
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String geteAccount() {
		return eAccount;
	}
	public void seteAccount(String eAccount) {
		this.eAccount = eAccount;
	}
	public String getePswd() {
		return ePswd;
	}
	public void setePswd(String ePswd) {
		this.ePswd = ePswd;
	}
	public int geteRole() {
		return eRole;
	}
	public void seteRole(int sRole) {
		this.eRole = sRole;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteDepartment() {
		return eDepartment;
	}
	public void seteDepartment(String eDepartment) {
		this.eDepartment = eDepartment;
	}
	public String getePhone() {
		return ePhone;
	}
	public void setePhone(String ePhone) {
		this.ePhone = ePhone;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	@Override
	public String toString() {
		return "Edu [eId=" + eId + ", eAccount=" + eAccount + ", ePswd=" + ePswd + ", eRole=" + eRole + ", eName="
				+ eName + ", eDepartment=" + eDepartment + ", ePhone=" + ePhone + ", eEmail=" + eEmail + "]";
	}
}
