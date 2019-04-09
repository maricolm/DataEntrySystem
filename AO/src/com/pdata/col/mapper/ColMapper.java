package com.pdata.col.mapper;

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
 * @descript ColMapper
 */
public interface ColMapper {
	public Col loginCol(Col col);//学院用户登陆
	public void modifyInfo(Col col);//修改个人信息
	public Col findColByCid(String cId);//通过id,查找用户
	public void modifypswd(Col col);//通过id修改密码
	
	public int findSelfProCount(String cId);//通过cid查询用户自己的项目总数
	public List<TaskOfSch> proList(PageBean<TaskOfSch> pb);//项目列表
	
	public void acceptTask(String tId);//接收项目
	public List<AttrOfPro> findProAttrByPid(String aPid);//通过项目ID查询所属的属性
	public String getAlias(String pId);//通过项目ID，查询项目的别名
	public int getLenAttrOfProByPid(String pId);//根据项目id获取项目属性的个数
	
	public int getCountDate(ToolForGetCount tool);//获取总条数
	
	public Pro getProByPid(String pid);//根据项目id获取项目的信息
	public void deleteDataById(ToolForGetCount tool);//通过ID删除数据
	public void callBackData(ToolForGetCount tool);//撤销数据
	public void updateDataState(ToolForGetCount tool);//改变数据表中的状态
	public void updateProState(String tId);//改变当前项目的状态

}
