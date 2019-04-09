/**  
 * @Title:  AdminMapper.java   
 * @Package com.pdata.admin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月18日 下午10:06:50   
 * @Copyright: 2018
 */  
package com.pdata.admin.mapper;

import java.util.List;

import com.pdata.admin.pojo.Admin;
import com.pdata.edu.pojo.Edu;

/**   
 * @ClassName:  AdminMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月18日 下午10:06:50     
 * @Copyright: 2018 
 */
public interface AdminMapper {
	public Admin loginAdmin(Admin admin);//管理员登陆
	public void addEdu(Edu edu);//添加教育厅账号
	public List<Edu> findEdu();//查看教育厅用户列表
	public void resetPswdById(Edu edu);//重置秘密
	public void deleteEduById(String eId);//删除教育厅用户
	
	public Boolean verifyAddAccount(String account);//异步验证添加的账号是否存在

}
