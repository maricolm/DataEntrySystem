/**  
 * @Title:  AdminServiceImpl.java   
 * @Package com.pdata.admin.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月18日 下午10:03:11   
 * @Copyright: 2018
 */  
package com.pdata.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndktools.javamd5.Mademd5;
import com.pdata.admin.mapper.AdminMapper;
import com.pdata.admin.pojo.Admin;
import com.pdata.admin.service.AdminService;
import com.pdata.edu.pojo.Edu;

import cn.itcast.commons.CommonUtils;

/**   
 * @ClassName:  AdminServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月18日 下午10:03:11     
 * @Copyright: 2018 
 */
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin login(Admin admin) {
		return adminMapper.loginAdmin(admin);
	}

	@Override
	public void addEdu(Edu edu) {
		String eId=CommonUtils.uuid();
		edu.seteId(eId);//补全主键id
		Mademd5 md=new Mademd5();
		edu.setePswd(md.toMd5("000000"));//生成系统默认秘密
		edu.seteRole(1);
		adminMapper.addEdu(edu);
	}

	@Override
	public List<Edu> findEdu() {
		return adminMapper.findEdu();
	}

	@Override
	public void resetPswdById(String eId) {
		Edu edu=new Edu();
		edu.seteId(eId);//补全主键id
		Mademd5 md=new Mademd5();
		edu.setePswd(md.toMd5("000000"));//生成系统默认秘密
		adminMapper.resetPswdById(edu);
	}

	@Override
	public void deleteEduById(String eId) {
		adminMapper.deleteEduById(eId);
	}

	@Override
	public Boolean verifyAddAccount(String account) {
		return adminMapper.verifyAddAccount(account);
	}
	
}
