package com.pdata.col.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.col.service.ColService;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.until.ExcelOfData;
import com.pdata.until.PageBean;

import test.Jdbcs;

/**
 * @author mulming
 * @time 2018年5月20日
 * @descript ColController
 */
@Controller
@RequestMapping("/col")
public class ColController {
	
	@Autowired
	private ColService colService;
	
	//登陆
	@RequestMapping("/login.do")
	public String login(Col col,HttpSession session,HttpServletRequest request){
		try {
			if(col.getcAccount()!=null && col.getcPswd()!=null) {
				Col co=colService.loginCol(col);
				if(co==null) {
					request.setAttribute("msg", "账号或秘密错误！");
					return "/login/col_login";
				}else{
					session.setAttribute("col", co);
					return "redirect:/col/col_index.jsp";
				}
			}else {
				return "/login/col_login";
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "系统错误！");
			return "/login/col_login";	
		}
	}
	
	//退出登陆
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/login/col_login.jsp";
	}
	
	//修改个人信息
	@RequestMapping("/modifyInfo.do")
	public String modifyInfo(Col col,HttpSession session,HttpServletRequest request){
		try {
			colService.modifyInfo(col);
			Col co=colService.findColByCid(col.getcId());
			session.setAttribute("col", co);
			return "redirect:/col/col_userCenter.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改失败");
			return "/error/error";
		}
	}
	
	//修改密码
	@RequestMapping("/modifypswd.do")
	public String modifypswd(Col col,HttpServletRequest request){
		try {
			colService.modifypswd(col);
			return "/login/col_login";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改失败");
			return "/error/error";
		}
	}
	
	//项目列表proList.do
	@RequestMapping("/proList.do")
	public String proList(int currentPage,HttpServletRequest request,HttpSession session){
		try {
			Col col=(Col) session.getAttribute("col");
			String cid=col.getcId();
			PageBean<TaskOfSch> pb=colService.proList(currentPage, cid);
			request.setAttribute("pb", pb);
			return "/col/col_proList";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询失败");
			return "/error/error";
		}
	}
	
	//接收项目
	@RequestMapping("/acceptTask.do")
	public String acceptTask(String tId,HttpServletRequest request){
		try {
			colService.acceptTask(tId);
			return "redirect:/col/proList.do?currentPage=1";
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "接收失败！");
			return "/error/error";
		}
	}
	
	//查看项目数据
	@RequestMapping("/findDataByProId.do")
	public String findDataByProId(String pId,int currentPage,HttpServletRequest request,HttpSession session){
		try {
			Col col=(Col) session.getAttribute("col");
			//1.获取表头，根据项目的id查询项目的属性
			List<AttrOfPro> attrOfPro=colService.findProAttrByPid(pId);
			//2.获取数据
			PageBean<List<String>> pb=colService.getDataOfProByPid(pId, col.getcId(),currentPage);
			request.setAttribute("attrs", attrOfPro);
			request.setAttribute("pb", pb);
			request.setAttribute("pId", pId);
			return "/col/dataTable";
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查看失败！");
			return "/error/error";
		}
	}
	//删除数据 deleteDataByProId.do?tid=${dat }&pId=${pId}
	@RequestMapping("/deleteDataByProId.do")
	public String deleteDataByProId(String pId,String tid,HttpServletRequest request,HttpSession session){
		try {
			Col col=(Col) session.getAttribute("col");
			String table=colService.getAlias(pId);
			String tid1=tid.split("=")[1];
			ToolForGetCount to=new ToolForGetCount(table, tid1);
			colService.deleteDataById(to);
			return "redirect:/col/findDataByProId.do?&currentPage=1&pId="+pId;
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除失败！");
			return "/error/error";
		}
	}
	//导出数据
	@RequestMapping("/export.do")
	public void export(HttpSession session,HttpServletResponse response) throws Exception{
		Workbook workbook = new XSSFWorkbook();
		String headers[]={"编号","姓名","电话","Email","QQ"};
		Jdbcs j=new Jdbcs();
		List<List<String>> li=j.resret();//返回数据集
		ExcelOfData excel=new ExcelOfData();
		List<String> head=new  ArrayList<String>();
	      head.add("数据表");
	      head.add("账号");
	      head.add("密码");
	      head.add("电话");
	      head.add("邮箱");
		excel.ExportDataToWorkbook(li, head, workbook);//把数据集加载到workBook中
		excel.export(response, workbook, "111.xlsx");
	}
	
	//上传数据
	@RequestMapping("/upload.do")
	public String upload(MultipartFile file,String pId,HttpSession session) throws Exception{
		ExcelOfData excel=new ExcelOfData();
		//1、获取表名。 即项目的英文别名
		String tabName=colService.getAlias(pId);
		//2、获取属性名
		List<AttrOfPro> attrOfPro=colService.findProAttrByPid(pId);
		//3、封装加入固定字段
		List<String> aliasOfPro=new ArrayList<String>();
		aliasOfPro.add("schId");
		aliasOfPro.add("colId");
		aliasOfPro.add("state");
		for(int i=0;i<attrOfPro.size();i++){
			aliasOfPro.add(attrOfPro.get(i).getaAlias());
		}
		//4、把学校id、学院id，添加进去。
		Col col=(Col) session.getAttribute("col");
		List<String> idAndName=new ArrayList<String>();
		idAndName.add(col.getcCid());
		idAndName.add(col.getcId());
		idAndName.add("1");
		excel.readExcelByFile(file, tabName, aliasOfPro, idAndName);
		return "redirect:/col/findDataByProId.do?pId="+pId+"&currentPage=1";
	}
	
	@RequestMapping("/callBackData.do")
	public String callBackData(String colid,String pId) throws Exception{
		String table=colService.getAlias(pId);
		colService.callBackData(table, colid);
		return "redirect:/col/proList.do?currentPage=1";
	}
	
	//submitData.do?colid=${col.cId }&pId=${task.pId }
	@RequestMapping("/submitData.do")
	public String submitData(String colid,String pId,String tId) throws Exception{
		String table=colService.getAlias(pId);
		colService.updateDataState(table, colid);
		colService.updateProState(tId);
		return "redirect:/col/proList.do?currentPage=1";
	}

}
