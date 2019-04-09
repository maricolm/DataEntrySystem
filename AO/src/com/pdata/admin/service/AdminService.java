/**  
 * @Title:  AdminService.java   
 * @Package com.pdata.admin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月18日 下午10:02:43   
 * @Copyright: 2018
 */  
package com.pdata.admin.service;

import java.util.List;

import com.pdata.admin.pojo.Admin;
import com.pdata.edu.pojo.Edu;

/**   
 * @ClassName:  AdminService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月18日 下午10:02:43     
 * @Copyright: 2018 
 */
public interface AdminService {
	public Admin login(Admin admin);//管理员登录
	public void addEdu(Edu edu);//添加教育厅账号
	public List<Edu> findEdu();//查看用户列表
	public void resetPswdById(String eId);//重置秘密
	public void deleteEduById(String eId);//删除教育厅用户

	public Boolean verifyAddAccount(String account);//异步验证添加的账号是否存在
}
