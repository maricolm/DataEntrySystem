package com.pdata.edu.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndktools.javamd5.Mademd5;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.mapper.EduMapper;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Edu;
import com.pdata.edu.pojo.Pro;
import com.pdata.edu.pojo.Tools;
import com.pdata.edu.service.EduService;
import com.pdata.school.pojo.Sch;
import com.pdata.until.ExcelOfData;
import com.pdata.until.JDBCTools;
import com.pdata.until.PageBean;

import cn.itcast.commons.CommonUtils;

@Service
public class EduServiceImpl implements EduService {
	@Autowired
	private EduMapper eduMapper;

	@Override
	public Edu loginEdu(Edu edu) {
		Mademd5 md=new Mademd5();
		edu.setePswd(md.toMd5(edu.getePswd()));
		return eduMapper.loginEdu(edu);
	}

	@Override
	public void modifyInfo(Edu edu) throws Exception{
		if(edu.geteId()!=null) {
			eduMapper.modifyInfo(edu);
		}else {
			throw new Exception("用户id为空 !");
		}
	}

	@Override
	public Edu findUserByEid(Edu edu) {
		return eduMapper.findUserByEid(edu);
	}

	@Override
	public void addSch(Sch sch) {
		//补全信息
		String sId=CommonUtils.uuid();
		sch.setsId(sId);//补全主键id
		Mademd5 md=new Mademd5();
		sch.setsPswd(md.toMd5("000000"));
		sch.setsRole(2);
		eduMapper.addSch(sch);
	}
	
	//分页查询用户
	public PageBean<Sch> findUsers(int currentPage, int pageSize) {
		int totalRecords=eduMapper.findSchCount();
		//获得PageBean对象
		PageBean<Sch> pb=new PageBean<Sch>(currentPage,totalRecords,pageSize);
		//根据开始记录数和每页大小，获取Users集合
		List<Sch> sch=eduMapper.findUsers(pb);
		pb.setPageSize(pageSize);
		//注入到PageBean中
		pb.setBeanList(sch);
		return pb;
	}

	@Override
	public void modifypswd(Edu edu) {
		Mademd5 md=new Mademd5();
		edu.setePswd(md.toMd5(edu.getePswd()));
		eduMapper.modifypswd(edu);
	}

	@Override
	public Pro addPro(Pro pro) {
		String pId=CommonUtils.uuid();
		pro.setpId(pId);
		Date  date=new Date();
		Date  data1=new java.sql.Date(date.getTime());
		pro.setpDate(data1);
		eduMapper.addPro(pro);
		return pro;
	}

	@Override
	public List<AttrOfPro> findProAttrByPid(String aPid) {
		return eduMapper.findProAttrByPid(aPid);
	}

	@Override
	public Pro findProById(String pId) {
		return eduMapper.findProById(pId);
	}

	@Override
	public void addProAttr(AttrOfPro attr) {
		String sId=CommonUtils.uuid();
		attr.setaId(sId);
		eduMapper.addProAttr(attr);
	}

	@Override
	public void resetPswdSch(String sId) {
		Sch sch=new Sch();
		sch.setsId(sId);
		Mademd5 md=new Mademd5();
		sch.setsPswd(md.toMd5("000000"));
		eduMapper.resetPswdSch(sch);
	}

	@Override
	public void deleteSchBySid(String sId) {
		eduMapper.deleteSchBySid(sId);
	}

	@Override
	public int findProCount(String condition) {
		return eduMapper.findProCount(condition);
	}

	@Override
	public PageBean<Pro> findPro(int currentPage, int pageSize,String eid) {
		int totalRecords=eduMapper.findProCount(eid);
		//获得PageBean对象
		PageBean<Pro> pb=new PageBean<Pro>(currentPage,totalRecords,pageSize);
		//根据开始记录数和每页大小，获取Users集合
		pb.setCondition(eid);
		List<Pro> pro=eduMapper.findPro(pb);
		pb.setPageSize(pageSize);
		//注入到PageBean中
		pb.setBeanList(pro);
		return pb;
	}

	@Override
	public void deleteProByPid(String pId) {
		deleteAttrByaPid(pId);
		eduMapper.deleteProByPid(pId);
	}

	@Override
	public void deleteAttrByaPid(String pId) {
		eduMapper.deleteAttrByaPid(pId);
	}

	@Override
	public void deleteAttrByaId(String aId) {
		eduMapper.deleteAttrByaId(aId);
	}

	@Override
	public String getProPaliasByPid(String pId) {
		return eduMapper.getProPaliasByPid(pId);
	}
	@Override
	public List<String> getAttrByProPid(String aPid) {
		return eduMapper.getAttrByProPid(aPid);
	}
	
	@Override
	public void updataProUrl(Pro pro) {
		eduMapper.updataProUrl(pro);
	}

	@Override
	public void updataState(String pId) {
		eduMapper.updataState(pId);
	}
	
	@Override
	public List<String> getAttrNameByProPid(String aPid) {
		return eduMapper.getAttrNameByProPid(aPid);
	}
	
	@Override
	public String publishPro(String pId,String fId) {
		JDBCTools jdbc=new JDBCTools();
		ExcelOfData excelTool=new ExcelOfData();
	//1、建立数据表 
		//1.1 获取项目别名
		String alias=getProPaliasByPid(pId);
		//1.2 获取项目下属性的别名
		List<String> attrOfPro=getAttrByProPid(pId);
		
		//1.3 用项目名为表名，属性为字段建立数据表
			//	 加入补充字段  ID、学校ID、学校名、学院ID、学院名、状态
		List<String> info=new ArrayList<String>();
		//info.add("id");
		info.add("schId");
		//info.add("schName");
		info.add("colId");//上传者ID
		//info.add("colName");
		info.add("state");
		// 新建数据表；
		jdbc.creatDataTable(alias, info, attrOfPro);
	//2、生成excel模版 
		List<String> attrName=eduMapper.getAttrNameByProPid(pId);
		String url=String.valueOf(excelTool.creatExcelForPro(alias, attrName));//返回模版的名字
	//3、修改项目状态为 2，并且把模版名加入项目表中去
		//3.1 	加入模版名
		Pro pro=new Pro();
		pro.setpId(pId);
		pro.setpUrl(url);
		updataProUrl(pro);
		//3.2 更新状态为2
		updataState(pId);
	//4、把学校用户和项目关联
		List<Sch> sch=findAllEdu();
		Pro pr=findProById(pId);
		for(int i=0;i<sch.size();i++) {
			DispensePro dpro=new DispensePro();
			dpro.setdId(CommonUtils.uuid());
			dpro.setfId(fId);
			dpro.setPid(pId);
			dpro.setpName(pr.getpName());
			dpro.setsId(sch.get(i).getsId());
			dpro.setsName(sch.get(i).getsUname());
			dpro.setcName(sch.get(i).getsName());
			dpro.setsDepartment(sch.get(i).getsDepartment());
			dpro.setDpState(1);
			dpro.setcState(1);
			dpro.seteEname(pr.getpEname());
			dpro.setpUrl(pr.getpUrl());
			dpro.setpDate(pr.getpDate());
			eduMapper.insertSchForPro(dpro);
		}
		return null;
	}

	@Override
	public Boolean verifyAddAccount(String account) {
		return eduMapper.verifyAddAccount(account);
	}

	@Override
	public int queryProStateDcl(String condition) {
		return eduMapper.queryProStateDcl(condition);
	}

	@Override
	public List<Sch> findAllEdu() {
		return eduMapper.findAllEdu();
	}

	@Override
	public void updateDProAPState(String dId) {
		//邮件通知
		//TODO
		eduMapper.updateDProAPState(dId);
	}

	@Override
	public int queryCountUserOfPro(String pId,String fId) {
		Tools t=new Tools();
		t.setAttr1(fId);
		t.setAttr2(pId);
		return eduMapper.queryCountUserOfPro(t);
	}

	@Override
	public PageBean<DispensePro> querySchOfPro(int currentPage,String pId,String fId) {
		int count=queryCountUserOfPro(pId,fId);
		PageBean<DispensePro> pb=new PageBean<DispensePro>(currentPage, count, 10);
		pb.setCondition(pId);
		pb.setId(fId);
		List<DispensePro> dispensePro=eduMapper.querySchOfPro(pb);
		pb.setBeanList(dispensePro);
		return pb;
	}
	
	@Override
	public Boolean ajaxVerifyProAlias(String alias) {
		return eduMapper.ajaxVerifyProAlias(alias);
	}

	@Override
	public Boolean verifyArgsaAlias(AttrOfPro attr) {
		return eduMapper.verifyArgsaAlias(attr);
	}
	
	@Override
	public PageBean<List<String>> getAllDataByEdu(String pId,String tableName,int currentPage) {
		JDBCTools jdbc=new JDBCTools();
		//获取
		int len=eduMapper.getLenAttrOfProByPid(pId);
		//2、根据项目别名去查询该表中该用户的数据
		//2.1、准备sql
		ToolForGetCount tool=new ToolForGetCount(tableName,null);
		int count=eduMapper.getCountOfColDate(tool);//计算当前的总数
		
		PageBean<List<String>> pb=new PageBean<List<String>>(currentPage, count, 10);
		//String sql="select * from "+alias+" where colId='"+cId+"' limit" +"'"+pb.getStartIndex()+","+pb.getPageSize();
		String sql="select * from "+tableName+" where state=3 limit "+pb.getStartIndex()+","+pb.getPageSize();
		//System.out.println(sql);
		//2.2、获取数据
		List<List<String>> data=jdbc.findResultSet(sql, len);
		pb.setBeanList(data);
		return pb;
	}

	@Override
	public String getAlias(String pId) {
		return eduMapper.getAlias(pId);
	}

	@Override
	public List<List<String>> getAllDataByPid(String pid,String tableName) {
		JDBCTools jdbc=new JDBCTools();
		int len=eduMapper.getLenAttrOfProByPid(pid);
		String sql="select * from "+tableName+" where state=3";
		List<List<String>> data=jdbc.findResultSetOfEdu(sql, len);
		return data;
	}
	
	public void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		 response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			OutputStream out=response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
	 }

	@Override
	public List<DispensePro> getDispensePro(String pid) {
		return eduMapper.getDispensePro(pid);
	}

	@Override
	public void modifyStateOftask(String did) {
		eduMapper.modifyStateOftask(did);
	}

	/* 
	 * mulming
	 */
	@Override
	public PageBean<List<String>> getDataOfSingleSchool(String pid, String sId, String tableName,int currentPage) {
		JDBCTools jdbc=new JDBCTools();
		//根据属性的个数数
		int len=eduMapper.getLenAttrOfProByPid(pid);
		//2、根据项目别名去查询该表中该用户的数据
		//2.1、准备sql
		ToolForGetCount tool=new ToolForGetCount(tableName,sId);
		int count=eduMapper.getCountOfSigleSchoolDate(tool);//计算当前的总数
		
		PageBean<List<String>> pb=new PageBean<List<String>>(currentPage, count, 10);
		//String sql="select * from "+alias+" where colId='"+cId+"' limit" +"'"+pb.getStartIndex()+","+pb.getPageSize();
		String sql="select * from "+tableName+" where state=3 and schid='"+sId+"' limit "+pb.getStartIndex()+","+pb.getPageSize();
		//System.out.println(sql);
		//2.2、获取数据
		List<List<String>> data=jdbc.findResultSet(sql, len);
		pb.setBeanList(data);
		return pb;
	}
	
	@Override
	public void modifyProTaskState(String did, String msg) {
		ToolForGetCount tool=new ToolForGetCount(msg,did);
		eduMapper.modifyProTaskState(tool);
	}

	@Override
	public void modifyDataStateTo2(String tableName, String schid) {
		ToolForGetCount tool=new ToolForGetCount(tableName,schid);
		eduMapper.modifyDataStateTo2(tool);
	}

	@Override
	public void endPro(String pId) {
		eduMapper.endPro(pId);
	}

}
