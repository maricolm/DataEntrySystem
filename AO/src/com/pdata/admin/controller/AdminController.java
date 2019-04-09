/**  
 * @Title:  AdminController.java   
 * @Package com.pdata.admin.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月18日 下午10:00:25   
 * @Copyright: 2018
 */  
package com.pdata.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pdata.admin.pojo.Admin;
import com.pdata.admin.service.AdminService;
import com.pdata.edu.pojo.Edu;
/**   
 * @ClassName:  AdminController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月18日 下午10:00:25     
 * @Copyright: 2018 
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	//登陆
	@RequestMapping("/login.do")
	public String login(Admin admin,HttpSession session,HttpServletRequest request){
		if(admin.getaAccount()!=null && admin.getaPswd()!=null) {
			Admin ad=adminService.login(admin);
			if(ad==null) {
				request.setAttribute("msg", "账号或秘密错误！");
				return "/login/admin_login";
			}else {
				session.setAttribute("admin", ad);
				return "redirect:/admin/admin_index.jsp";
			}
		}
		return "NewFile";
	}
	//退出登陆
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login/admin_login.jsp";
	}
	
	//异步严重验证码
	@RequestMapping("/verifyCode.do")
	public void VerifyCode(String vCode,HttpSession session,HttpServletResponse rep) throws IOException{
		String code=(String)session.getAttribute("vCode");
		boolean b=vCode.equalsIgnoreCase(code);
		rep.getWriter().print(b);
	}
	
	//添加教育厅账号
	@RequestMapping("/addEdu.do")
	public String addEdu(Edu edu,HttpServletRequest re) {
		try {
			adminService.addEdu(edu);
			re.setAttribute("msg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			re.setAttribute("msg", "添加失败");
		}
		return "/admin/admin_index";
	}
	
	//查看教育厅账号列表
	@RequestMapping("/findEdu.do")
	public String findEdu(HttpServletRequest re) {
		List<Edu> edu=new ArrayList<Edu>();
		try {
			edu=adminService.findEdu();
			re.setAttribute("edu", edu);
		} catch (Exception e) {
			re.setAttribute("msg", "获取失败");
		}
		return "/admin/admin_userList";
	}
	
	//重置秘密
	@RequestMapping("/resetPswd.do")
	public String resetPswd(String eId,HttpServletRequest re) {
		try {
			adminService.resetPswdById(eId);
			re.setAttribute("msg", "重置成功");
		} catch (Exception e) {
			re.setAttribute("msg", "重置失败，请再来一次");
			return "/admin/admin_index";
		}
		return "/admin/admin_index";
	}
	
	//重置秘密
	@RequestMapping("/deleteEdu.do")
	public String deleteEdu(String eId,HttpServletRequest re) {
		try {
			adminService.deleteEduById(eId);
			re.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			re.setAttribute("msg", "删除失败，请再来一次");
			return "/admin/admin_index";
		}
		return "/admin/admin_index";
	}
	
	
	//---异步验证--------------------------------------------------------------------------------------
	//添加账号的异步验证
	@RequestMapping("/ajaxVerifyAddAccount.do")
	public void ajaxVerifyAddAccount(String account,HttpServletResponse rsp) throws Exception {
		Boolean bool=adminService.verifyAddAccount(account);
		rsp.getWriter().println(bool);
	}
}
