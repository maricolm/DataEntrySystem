package com.pdata.edu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Edu;
import com.pdata.edu.pojo.Pro;
import com.pdata.edu.service.EduService;
import com.pdata.school.pojo.Sch;
import com.pdata.until.ExcelOfData;
import com.pdata.until.PageBean;

@Controller
@RequestMapping("/edu")
public class EduController {
	
	@Autowired
	private EduService eduService;
	//登陆
	@RequestMapping("/login.do")
	public String login(Edu edu,HttpSession session,HttpServletRequest request){
		try {
			if(edu.geteAccount()!=null && edu.getePswd()!=null) {
				Edu ad=eduService.loginEdu(edu);
				if(ad==null){
					request.setAttribute("msg", "账号或错误！");
					return "/login/edu_login";
				}else {
					int proCount=eduService.findProCount(ad.geteId());
					int proDcl=eduService.queryProStateDcl(ad.geteId());
					session.setAttribute("edu", ad);
					session.setAttribute("proCount", proCount);
					session.setAttribute("dclMsg", proDcl);
					return "redirect:/edu/edu_index.jsp";
				}
			}else {
				return "/login/edu_login";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return "/login/edu_login";
		}
		
	}
	//退出登陆
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login/edu_login.jsp";
	}
	//修改个人信息
	@RequestMapping("/modifyinfo.do")
	public String modifyInfo(Edu edu,HttpSession session,HttpServletRequest request) {
		try {
			eduService.modifyInfo(edu);
			Edu ad=eduService.findUserByEid(edu);
			session.setAttribute("edu", ad);
			return "redirect:/edu/edu_userCenter.jsp";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "保存失败");
			return "/error/error";
		}
		
	}
	//修改密码modifypswd.do
	@RequestMapping("/modifypswd.do")
	public String modifypswd(String pwsd,HttpSession session,HttpServletRequest request) {
		Edu edu=(Edu) session.getAttribute("edu");
		if(edu!=null) {
			edu.setePswd(pwsd);
			try {
				eduService.modifypswd(edu);
				return "redirect:/login/edu_login.jsp";
			} catch (Exception e) {
				e.printStackTrace(); 
				request.setAttribute("msg", "密码修改失败！");
				return "/error/error";
			}
		}else {
			System.out.println("错误");
		}
		return "redirect:/login/edu_login.jsp";
	}
	
	//添加学校账号
	@RequestMapping("/addSch.do")
	public String addSch(Sch sch,HttpSession session,HttpServletRequest request) {
		try {
			Edu edu=(Edu) session.getAttribute("edu");
			sch.setsCid(edu.geteId());
			eduService.addSch(sch);
			return "redirect:/edu/findUsers.do?currentPage=1";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "添加失败");
			return "/error/error";
		}
	}
	
	//分页查询学校用户
	@RequestMapping(value="/findUsers.do")
	public String findUsers(int currentPage,HttpServletRequest request){
		try {
			// 每页大小
			PageBean<Sch> pb = new PageBean<Sch>(0, 0, 0);
			pb.setPageSize(10);
			pb.setCurrentPage(currentPage);
			PageBean<Sch> pbn = eduService.findUsers(pb.getCurrentPage(),pb.getPageSize());
			// 把PageBean对象放到域对象中
			request.setAttribute("pb", pbn);
			// 请求转发
			return "/edu/edu_userList";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "查询错误！");
			return "/error/error";
		}
		
	}
	//重置用户密码
	@RequestMapping(value="/ajaxResetpswdForUser.do")
	public void ajaxResetpswdForUser(String sId,HttpServletResponse resp) throws Exception {
		try {
			eduService.resetPswdSch(sId);
			resp.getWriter().println(true);
		}catch (Exception e) {
			e.printStackTrace(); 
			resp.getWriter().println(false);
		}
		
	}
	//删除用户
	@RequestMapping(value="/deleteSch.do")
	public String deleteSch(String sId,HttpServletRequest request){
		try {
			eduService.deleteSchBySid(sId);
			return "redirect:/edu/findUsers.do?currentPage=1";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "删除出错误了！");
			return "/error/error";
		}
		
	}
	
	//添加项目
	@RequestMapping(value="/addPro.do")
	public String addPro(Pro pro,HttpSession session,HttpServletRequest request){
		try {
			Edu edu=(Edu) session.getAttribute("edu");
			pro.setpEid(edu.geteId());
			pro.setpEname(edu.geteName());
			pro.setpState(1);//未发布状态
			Pro pr=eduService.addPro(pro);
			int proCount=eduService.findProCount(edu.geteId());
			session.setAttribute("proCount", proCount);
			return "redirect:/edu/findProAttrByPid.do?aPid="+pr.getpId();
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "添加项目出错误了！重新登陆账号添加。");
			return "/error/error";
		}
	}
	
	//通过项目id查询项目的字段
	@RequestMapping(value="/findProAttrByPid.do")
	public String findProAttrByPid(String aPid,HttpServletRequest request){
		try {
			Pro pro=eduService.findProById(aPid);
			if(aPid!=null) {
				List<AttrOfPro> attr=eduService.findProAttrByPid(aPid);
				request.setAttribute("attr", attr);
				request.setAttribute("pro", pro);
			}
			return "/edu/edu_addProAttr";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "查找出错误了！");
			return "/error/error";
		}
		
	}
	//添加属性
	@RequestMapping(value="/addAttr.do")
	public String addAttr(AttrOfPro at,HttpServletRequest request){
		try {
			eduService.addProAttr(at);
			return "redirect:/edu/findProAttrByPid.do?aPid="+at.getaPid();
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "添加失败！");
			return "/error/error";
		}
		
	}
	//删除项目所属的属性 deleteAttrByaId.do
	@RequestMapping(value="/deleteAttrByaId.do")
	public String deleteAttrByaId(String aId,String aPid,HttpServletRequest request){
		try {
			eduService.deleteAttrByaId(aId);
			return "redirect:/edu/findProAttrByPid.do?aPid="+aPid;
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "属性删除失败！");
			return "/error/error";
		}
		
	}
	
	//查询项目列表
	@RequestMapping(value="/findPro.do")
	public String findPro(int currentPage,HttpServletRequest request,HttpSession session){
		try {
			Edu edu=(Edu) session.getAttribute("edu");
			// 每页大小
			PageBean<Pro> pb = new PageBean<Pro>(0, 0, 0);
			pb.setPageSize(10);
			pb.setCurrentPage(currentPage);
			PageBean<Pro> pbn = eduService.findPro(pb.getCurrentPage(),pb.getPageSize(),edu.geteId());
			// 把PageBean对象放到域对象中
			request.setAttribute("pb", pbn);
			// 请求转发
			return "/edu/edu_proList";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "查询失败，重新登陆试试！");
			return "/error/error";
		}
		
	}
	//删除未发布的项目
	@RequestMapping(value="/deleteProByPid.do")
	public String deleteProByPid(String aPid,HttpServletRequest request) {
		try {
			eduService.deleteProByPid(aPid);
			return "redirect:/edu/findPro.do?currentPage=1";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "删除不成功！");
			return "/error/error";
		}
		
	}
	
	//发布项目
	@RequestMapping(value="/publishPro.do")
	public String publishPro(String pId,HttpSession session,HttpServletRequest request){
		try {
			Edu edu=(Edu) session.getAttribute("edu");
			eduService.publishPro(pId,edu.geteId());
			int proDcl=eduService.queryProStateDcl(edu.geteId());
			session.setAttribute("dclMsg", proDcl);
			return "redirect:/edu/findPro.do?currentPage=1";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "发布失败，重新登陆后再发布试试！");
			return "/error/error";
		}
		
	}

	//分配任务用户列表
	@RequestMapping(value="/dispensePro.do")
	public String dispensePro(int currentPage,String pId,HttpServletRequest request,HttpSession session) {
		try {
			Edu edu=(Edu) session.getAttribute("edu");
			PageBean<DispensePro> dispensePro=eduService.querySchOfPro(currentPage,pId,edu.geteId());
			request.setAttribute("pId", pId);
			request.setAttribute("pb", dispensePro);
			return "/edu/edu_dispensePro";
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "列表查询失败！");
			return "/error/error";
		}
		
	}
	
	//分配任务
	@RequestMapping(value="/disatribute.do")
	public String disatribute(String pId,String dId,HttpServletRequest request) {
		try {
			eduService.updateDProAPState(dId);
			return "redirect:/edu/dispensePro.do?currentPage=1&pId="+pId;
		} catch (Exception e) {
			e.printStackTrace(); 
			request.setAttribute("msg", "任务分配失败！");
			return "/error/error";
		}
		
	}
	
	//下载附件
	@RequestMapping("/download.do")
	public ResponseEntity<byte[]> download(String path, String name){
		try {
			name=name+".xlsx";
			String filePath="c:\\excel\\"+path+".xlsx";
			File file = new File(filePath);  
			String dfileName = new String(name.getBytes("UTF-8"), "iso8859-1");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
		
	}
	
	
	//---异步验证--------------------------------------------------------------------------------------
	//添加账号的异步验证
	@RequestMapping("/ajaxVerifyAddAccount.do")
	public void ajaxVerifyAddAccount(String account,HttpServletResponse rsp) throws Exception {
		Boolean bool=eduService.verifyAddAccount(account);
		rsp.getWriter().println(bool);
	}
	
	//添加项目别名的异步验证
	@RequestMapping("/ajaxVerifyProAlias.do")
	public void ajaxVerifyProAlias(String alias,HttpServletResponse rsp) throws Exception {
		Boolean bool=eduService.ajaxVerifyProAlias(alias);
		rsp.getWriter().println(bool);
	}
	
	//添加项目属性的别名的异步验证
	@RequestMapping("/verifyArgsaAlias.do")
	public void verifyArgsaAlias(AttrOfPro attr,HttpServletResponse rsp) throws Exception {
		Boolean bool=eduService.verifyArgsaAlias(attr);
		rsp.getWriter().println(bool);
	}
	
	//查看所有自己项目下的所有的数据的数据seeDataAllOfProject.do?pid=
	@RequestMapping(value="/seeDataAllOfProject.do")
	public String seeDataAllOfProject(String pid,int currentPage,HttpServletRequest req) {
		//1、得到表头
		List<AttrOfPro> attrOfPro=eduService.findProAttrByPid(pid);
		//2、获取数据
		String tableName=eduService.getAlias(pid);
		PageBean<List<String>> pb=eduService.getAllDataByEdu(pid, tableName, currentPage);
		req.setAttribute("attrs", attrOfPro);
		req.setAttribute("pb", pb);
		req.setAttribute("pId", pid);
		return "/edu/allDataTable";
	}

	//导出数据 
	@RequestMapping(value="/exportData.do")
	public String exportData(String pid,HttpServletResponse response,HttpServletRequest req) {
		//1、得到表头
		List<AttrOfPro> attrOfPro=eduService.findProAttrByPid(pid);
		String tableName=eduService.getAlias(pid);
		List<String> head=new ArrayList<String>();
		head.add(tableName);
		for(int i=0;i<attrOfPro.size();i++) {
			head.add(attrOfPro.get(i).getaName());
		}
		//2、获取数据
		List<List<String>> data=eduService.getAllDataByPid(pid, tableName);
		Workbook workbook = new XSSFWorkbook();
		ExcelOfData ex=new ExcelOfData();
		ex.ExportDataToWorkbook(data, head, workbook);
		try {
			ex.export(response, workbook, System.currentTimeMillis()+".xlsx");
		} catch (Exception e) {
			e.printStackTrace(); 
			req.setAttribute("msg", "任务分配失败！");
			return "/error/error";
		}
		return null;
	}
	
	//查看项目进度 edu/seeSchedule.do?currentPage=1&pId=
	@RequestMapping(value="/seeSchedule.do")
	public String seeSchedule(String pId,HttpServletRequest req) {
		List<DispensePro> pro=eduService.getDispensePro(pId);
		req.setAttribute("proList", pro);
		return "/edu/edu_proTaskState";
	}
	
	//确认学校提交的数据 confirmAcceptTask.do?did=
	@RequestMapping(value="/confirmAcceptTask.do")
	public String confirmAcceptTask(String did,String pid,HttpServletRequest req) {
		eduService.modifyStateOftask(did);
		return "redirect:/edu/seeSchedule.do?pId="+pid;
	}
	
	//查看单个学校的数据
	@RequestMapping(value="/seeDataOfSingleSchool.do")
	public String seeDataOfSingleSchool(String pid,String sId,int currentPage,HttpServletRequest req) {
		//1、得到表头
		List<AttrOfPro> attrOfPro=eduService.findProAttrByPid(pid);
		//2、获取数据
		String tableName=eduService.getAlias(pid);
		
		PageBean<List<String>> pb=eduService.getDataOfSingleSchool(pid, sId, tableName, currentPage);
		
		req.setAttribute("attrs", attrOfPro);
		req.setAttribute("pb", pb);
		req.setAttribute("pid", pid);
		req.setAttribute("sid", sId);
		return "/edu/sigleSchoolDataTable";
	}
	
	//驳回学校的任务
	@RequestMapping(value="/rejectSchoolTask.do")
	public String rejectSchoolTask(String did,String pId,String sId,HttpServletRequest req) {
		req.setAttribute("did", did);
		req.setAttribute("pid", pId);
		req.setAttribute("sid", sId);
		return "/edu/reject";
	}

	@RequestMapping(value="/reject.do")
	public String reject(String did,String pid,String sid,String msg,HttpServletRequest req) {
		//1、修改状态和驳回原因
		eduService.modifyProTaskState(did, msg);
		//2、修改数据库中数据状态为2
		 //2.1、得到表名
			String tableName=eduService.getAlias(pid);
		 //2.2、根据学号账户修改状态
			eduService.modifyDataStateTo2(tableName, sid);
		return "redirect:/edu/seeSchedule.do?pId="+pid;
	}
	
	//结束项目 ?pId=${pro.pId}
	@RequestMapping(value="/endPro.do")
	public String endPro(String pId,HttpServletRequest req) {
		eduService.endPro(pId);
		return "redirect:/edu/findPro.do?currentPage=1";
	}
	
}
