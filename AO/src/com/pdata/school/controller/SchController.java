package com.pdata.school.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Pro;
import com.pdata.school.pojo.Sch;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.school.service.SchService;
import com.pdata.until.PageBean;

@Controller
@RequestMapping("/sch")
public class SchController {
	@Autowired
	private SchService schService;
	
	//登陆
	@RequestMapping("/login.do")
	public String login(Sch sch,HttpSession session,HttpServletRequest request){
		String path=null;
		try {
			if(sch.getsAccount()!=null && sch.getsPswd()!=null) {
				Sch sc=schService.schLogin(sch);
				if(sc==null) {
					request.setAttribute("msg", "账号或秘密错误！");
					path="/login/school_login";
				}else {
					session.setAttribute("sch", sc);
					path="redirect:/sch/sch_index.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/login/school_login";
		}
		return path;
	}

	//退出登陆
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login/school_login.jsp";
	}
	
	//修改个人信息
	@RequestMapping("/modifyinfo.do")
	public String modifyInfo(Sch sch,HttpSession session) {
		try {
			schService.modifyInfo(sch);
			Sch sc=schService.findUserBySid(sch.getsId());
			session.setAttribute("sch", sc);
			return "redirect:/sch/sch_userCenter.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error";
		}
	}
		
	//修改密码modifypswd.do
	@RequestMapping("/modifypswd.do")
	public String modifypswd(String pwsd,HttpSession session) {
		try {
			Sch sch=(Sch) session.getAttribute("sch");
			if(sch!=null) {
				sch.setsPswd(pwsd);
				schService.modifypswd(sch);
				return "redirect:/login/school_login.jsp";
			}else {
				System.out.println("错误");
			}
			return "redirect:/login/school_login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error";
		}
	}
	
	//添加学校账号
	@RequestMapping("/addCol.do")
	public String addCol(Col col,HttpSession session,HttpServletRequest request) {
		try {
			Sch sch=(Sch) session.getAttribute("sch");
			col.setcCid(sch.getsId());
			col.setcName(sch.getsName());
			schService.addCol(col);
			return "redirect:/sch/findUser.do?currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败");
			return "/error/error";
		}
	}
	
	//查看学院列表
	@RequestMapping("findUser.do")
	public String findUser(int currentPage,HttpSession session,HttpServletRequest request){
		String path=null;
		try{
			Sch sch=(Sch) session.getAttribute("sch");
			PageBean<Col> pb=schService.findUser(currentPage, sch.getsId());
			request.setAttribute("pb", pb);
			path="/sch/sch_userList";
		}catch (Exception e){
			request.setAttribute("msg", "你还没有登陆！");
			path="/sch/page_404";
		}
		return path;
	}
	
	//删除用户
	@RequestMapping("deleteUserByid.do")
	public String deleteUserByid(String cId) {
		if(cId!=null) {
			schService.deletdUserById(cId);
		}else {
			System.out.println("错误----");
		}
		return "redirect:/sch/findUser.do?currentPage=1";
	}
	
	//异步为用户重置密码
	@RequestMapping("/ajaxResetpswdForUser.do")
	public void ajaxResetpswdForUser(String cId,HttpServletResponse rsp) throws Exception {
		try {
			Col col=new Col();
			col.setcId(cId);
			schService.resetPswdForUser(col);
			rsp.getWriter().println(true);
		}catch (Exception e) {
			rsp.getWriter().println(false);
		}
	}
	
	//查看待接收的项目列表 
	@RequestMapping("/queryProOfBeCompleted.do")
	public String queryProOfBeCompleted(int currentPage,HttpServletRequest request,HttpSession session){
		Sch sch=(Sch) session.getAttribute("sch");
		try {
			PageBean<DispensePro> disPro=schService.queryProOfBeCompleted(currentPage,sch.getsId());
			request.setAttribute("pb", disPro);
		}catch (Exception e){
			request.setAttribute("msg", "你还没有登陆！");
			return "/error/error";
		}
		return "/sch/sch_proList";
	}
	
	//学校用户接收任务
	@RequestMapping("/receiveTask.do")
	public String receiveTask(String did,HttpServletRequest request,HttpSession session){
		Sch sch=(Sch) session.getAttribute("sch");
		try {
			schService.insertProToTaskOfSch(sch.getsId(), did);
		}catch (Exception e){
			request.setAttribute("msg", "接收任务失败！");
			return "/error/error";
		}
		return "redirect:/sch/queryProOfBeCompleted.do?currentPage=1";
	}

	//分配任务列表
	@RequestMapping("/assignTask.do")//分配者id,项目id
	public String assignTask(int currentPage,String pid,HttpServletRequest request,HttpSession session) {
		Sch sch=(Sch) session.getAttribute("sch");
		PageBean<TaskOfSch> pb=schService.queryColOfPro(currentPage, pid, sch.getsId());
		request.setAttribute("pb", pb);
		return "/sch/sch_taskOfSch";
	}
	
	//为用户分配任务
	@RequestMapping("/assignTaskToCol.do")//分配者id,项目id
	public String assignTaskToCol(int currentPage,String pid,String tid) {
		schService.assignTaskToCol(tid);
		return "redirect:/sch/assignTask.do?currentPage="+currentPage+"&pid="+pid;
	}
	
	//---异步验证--------------------------------------------------------------------------------------
	//添加账号的异步验证
	@RequestMapping("/ajaxVerifyAddAccount.do")
	public void ajaxVerifyAddAccount(String account,HttpServletResponse rsp) throws Exception {
		Boolean bool=schService.verifyAddAccount(account);
		rsp.getWriter().println(bool);
	}
	
	
	//查看分配了的用户的任务完成情况
	@RequestMapping("/seeStateState.do")//分配者id,项目id
	public String seeStateState(int currentPage,String pid,HttpServletRequest request,HttpSession session) {
		Sch sch=(Sch) session.getAttribute("sch");
		PageBean<TaskOfSch> pb=schService.queryTaskListState(currentPage, pid, sch.getsId());
		request.setAttribute("pb", pb);
		return "/sch/sch_taskState";
	}
	//查看某一个学院提交上来的数据 seeDataOfCol.do
	@RequestMapping("/seeDataOfCol.do")//分配者id,项目id
	public String seeDataOfCol(String pId,String cId,int currentPage,HttpServletRequest request,HttpSession session) {
		Sch sch=(Sch) session.getAttribute("sch"); 
		//1、得到表头
		List<AttrOfPro> attrOfPro=schService.findProAttrByPid(pId);
		String sId=sch.getsId();//项目发布者的id
		//2、得到表名 项目id == pid
		String tableName=schService.getAlias(pId);
		//3、得到数据
		PageBean<List<String>> pb=schService.getDataOfCol(pId,tableName,sId,cId,currentPage);
		request.setAttribute("attrs", attrOfPro);
		request.setAttribute("pb", pb);
		request.setAttribute("cId", cId);
		request.setAttribute("pId", pId);
		return "/sch/schDataTable";
	}
	
	//学校查看自己下所有的数据
	@RequestMapping("/seeAllData.do")//分配者id,项目id
	public String seeAllData(String pId,int currentPage,HttpServletRequest request,HttpSession session) {
		Sch sch=(Sch) session.getAttribute("sch"); 
		//1、得到表头
		List<AttrOfPro> attrOfPro=schService.findProAttrByPid(pId);
		String sId=sch.getsId();//项目发布者的id
		//2、得到表名 项目id == pid
		String tableName=schService.getAlias(pId);
		//3、得到数据
		PageBean<List<String>> pb=schService.getAllData(pId,tableName,sId,currentPage);
		request.setAttribute("attrs", attrOfPro);
		request.setAttribute("pb", pb);
		request.setAttribute("pId", pId);
		return "/sch/allDataTable";
	}
	
	//学校确认某个学校的任务状态
	@RequestMapping("/affirm.do")//分配者id,项目id
	public String affirm(String tId,String pId,HttpServletRequest request) {
		schService.affirm(tId);
		return "redirect:/sch/seeStateState.do?currentPage=1&pid="+pId;
	}

	//中转 驳回tId=${tPro.tId }&pId=${tPro.pId }&cId=${tPro.cId }
	@RequestMapping("/returnPage.do")//分配者id,项目id
	public String returnPage(String tId,String pId,String cId,HttpServletRequest req) {
		req.setAttribute("tId", tId);
		req.setAttribute("pId", pId);
		req.setAttribute("cId", cId);
		return "/sch/reject";
	}
	
	//学校驳回某个学校的任务状态
	@RequestMapping("/reject.do")//分配者id,项目id
	public String reject(String tId,String pId,String cId,String msg,HttpServletRequest request) {
		ToolForGetCount tool=new ToolForGetCount(msg, tId);
		//1、改变任务状态
		schService.reject(tool);
		//2、改变表的数据的信息
			//2.1、得到表名 项目id == pid
				String tableName=schService.getAlias(pId);
			//2.2 改变数据的状态
				ToolForGetCount tool1=new ToolForGetCount(tableName, cId);
				schService.modifyStateOfDtae(tool1);
		return "redirect:/sch/seeStateState.do?currentPage=1&pid="+pId;
	}
	
	//学号账户提交自己的任务
	@RequestMapping("/submitData.do")//任务数据表id,项目id，自己的ID
	public String submitData(String did,String pid,String sid,String msg,HttpServletRequest request) {
		//1、确认自己任务列表中，本项目下所有的项目状态为确认状态。
		DispensePro dispensePro=new DispensePro();
		dispensePro.setPid(pid);
		dispensePro.setsId(sid);
		int count=schService.getStateNumOfNotConfirm(dispensePro);
		if(count!=0) {
			request.setAttribute("msg", "你还有未确认的任务！");
			return "/sch/msg";
		}
		//2、得到数据表，状态改为3
		String tableName=schService.getAlias(pid);
		ToolForGetCount tool=new ToolForGetCount(tableName,null,sid);
		schService.modifydataStateTo3(tool);
		//3、修改任务列表中的任务状态
		schService.modifyStateOftask(did);
		return "redirect:/sch/queryProOfBeCompleted.do?currentPage=1";
	}
	
}
