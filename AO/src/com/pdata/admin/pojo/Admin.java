/**  
 * @Title:  Admin.java   
 * @Package com.pdata.admin.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月18日 下午10:01:05   
 * @Copyright: 2018
 */  
package com.pdata.admin.pojo;

/**   
 * @ClassName:  Admin   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月18日 下午10:01:05     
 * @Copyright: 2018 
 */
public class Admin {
	String aId;
	String aAccount;
	String aPswd;
	String aPhone;
	String aEmail;
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaAccount() {
		return aAccount;
	}
	public void setaAccount(String aAccount) {
		this.aAccount = aAccount;
	}
	public String getaPswd() {
		return aPswd;
	}
	public void setaPswd(String aPswd) {
		this.aPswd = aPswd;
	}
	public String getaPhone() {
		return aPhone;
	}
	public void setaPhone(String aPhone) {
		this.aPhone = aPhone;
	}
	public String getaEmail() {
		return aEmail;
	}
	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}
	@Override
	public String toString() {
		return "Admin [aId=" + aId + ", aAccount=" + aAccount + ", aPswd=" + aPswd + ", aPhone=" + aPhone + ", aEmail="
				+ aEmail + "]";
	}
}
