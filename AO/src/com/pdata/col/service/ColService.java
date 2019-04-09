package com.pdata.col.service;

import java.util.List;

import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.Pro;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.until.PageBean;

/**
 * @author mulming
 * @time 2018年5月20日
 * @descript ColService
 */
public interface ColService {
	public Col loginCol(Col col);//学院用户登陆
	public void modifyInfo(Col col);//修改个人信息
	public Col findColByCid(String cId);//通过id,查找用户
	public void modifypswd(Col col);//通过id修改密码
	
	public int findSelfProCount(String cId);//通过cid查询用户自己的项目总数
	public PageBean<TaskOfSch> proList(int currentPage,String cId);//项目列表
	
	public void acceptTask(String tId);//接收项目
	public List<AttrOfPro> findProAttrByPid(String aPid);//通过项目ID查询所属的属性
	public PageBean<List<String>> getDataOfProByPid(String pId,String cId,int pageCurrent);//通过项目的id去查询项目的数据 pid,项目的id, cId,学院id
	public String getAlias(String pId);//通过项目ID，查询项目的别名
	public Pro getProByPid(String pid);//根据项目id获取项目的信息
	public void deleteDataById(ToolForGetCount tool);//通过ID删除数据
	public void callBackData(String table,String colid);//撤销数据
	public void updateDataState(String table,String colid);//改变数据表中的状态
	public void updateProState(String tId);//改变当前项目的状态

}
