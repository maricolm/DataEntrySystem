package com.pdata.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Edu;
import com.pdata.edu.pojo.Pro;
import com.pdata.school.pojo.Sch;
import com.pdata.until.PageBean;

public interface EduService {
	public Edu loginEdu(Edu edu);//教育厅登陆
	public void modifyInfo(Edu edu) throws Exception;//修改个人信息
	public Edu findUserByEid(Edu edu);//通过id查询用户
	public void addSch(Sch sch);//添加学校账号
	public Boolean verifyAddAccount(String account);//异步验证添加的账号是否存在
	public PageBean<Sch> findUsers(int currentPage, int pageSize);//分页查询
	public void resetPswdSch(String sId);//重置学校账号密码
	public void deleteSchBySid(String sId);//通过用户ID删除用户
	public void modifypswd(Edu edu);//修改用户密码
	public Pro addPro(Pro pro);//添加项目
	public Boolean ajaxVerifyProAlias(String alias);//异步验证项目别名
	public List<AttrOfPro> findProAttrByPid(String aPid);//通过项目id查询对应的项目字段
	
	public Pro findProById(String pId);//通过项目ID查询项目
	public void addProAttr(AttrOfPro attr);//添加项目属性
	public Boolean verifyArgsaAlias(AttrOfPro attr);//异步验证添加的账号是否存在
	public void deleteAttrByaId(String aId);//通过属性ID删除属性
	public int findProCount(String condition);//查询项目总数
	public int queryProStateDcl(String condition);//查询待处理项目
	public PageBean<Pro> findPro(int currentPage,int pageSize,String eid);//分页查询项目列表
	public void deleteProByPid(String pId);//删除项目
	public void deleteAttrByaPid(String pId);//通过项目ID删除项目下面所有的属性
	
	public String publishPro(String pId,String fId);//发布项目
	public String getProPaliasByPid(String pId);//通过项目ID，获取项目别名
	public List<String> getAttrByProPid(String aPid);//通过项目ID，获取项目的属性
	
	public void updataProUrl(Pro pro);//更新项目中模版的连接位置
	public void updataState(String pId);//项目状态
	public List<String> getAttrNameByProPid(String aPid);//通过项目ID，获取项目的属性
	public List<Sch> findAllEdu();//查询所有的学校用户
	public void updateDProAPState(String dId);//通过ID给用户分配任务
	
	public int queryCountUserOfPro(String pId,String fId);//查看项目对应的用户的总个数
	public PageBean<DispensePro> querySchOfPro(int currentPage,String pId,String fId);//查看与项目相对应的用户
	
	public PageBean<List<String>> getAllDataByEdu(String pId,String tableName,int currentPage);//教育厅用户查看所有的数据
	public String getAlias(String pId);//通过项目ID，查询项目的别名
	public List<List<String>> getAllDataByPid(String pid,String tableName);//获取项目全部的数据
	public void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception;
	public List<DispensePro> getDispensePro(String pid);//任务进度
	public void modifyStateOftask(String did);//确认项目
	
	public PageBean<List<String>> getDataOfSingleSchool(String pid,String sId,String tableName,int currentPage);//获取项目全部的数据
	public void modifyProTaskState(String did,String msg);//修改任务状态
	public void modifyDataStateTo2(String tableName,String schid);
	public void endPro(String pId);//结束整个项目
}
