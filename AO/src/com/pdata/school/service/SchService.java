package com.pdata.school.service;

import java.util.List;

import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Pro;
import com.pdata.edu.pojo.Tools;
import com.pdata.school.pojo.Sch;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.until.PageBean;

public interface SchService {
	public Sch schLogin(Sch sch);//学校账号登陆
	public void modifyInfo(Sch sch);//修改个人信息
	public Sch findUserBySid(String sId);//通过账号查询用户信息
	public void modifypswd(Sch sch);//修改密码
	public Boolean verifyAddAccount(String account);//异步验证添加的账号是否存在
	
	public void addCol(Col col);//添加学院账号
	public int findCountColUser(String condition);//查询二级学院账号的总数
	public PageBean<Col> findUser(int currentPage,String condition);//查询用户
	
	public void deletdUserById(String cId);//通过id删除用户
	public void resetPswdForUser(Col col);//重置密码
	
	public int findCountBeCompleted(String sid);//查询待接收的项目的总数
	public PageBean<DispensePro> queryProOfBeCompleted(int currentPage,String sId);//查看自己待接收的项目
	public List<Col> findAllColList(String cCid);//查询用户列表
	public void updateCState(String dId);//修改自己接收的任务的进度状态
	public DispensePro findDisPenseProByDid(String did);//通过did查询教育厅分配的任务
	public void insertProToTaskOfSch(String sId,String did);//把接收的任务保存到任务表
	
	public int queryCountColOfPro(String pId,String fId);//查询项目下所有的用户个数
	public PageBean<TaskOfSch> queryColOfPro(int currentPage,String pid,String fId);//返回用户 分配者id,项目id 
	public void assignTaskToCol(String tid);//为对应的学院分配任务,传入task表的id
	
	public int queryCountColOfProState(String pId,String fId);//查看任务完成状态的总用户的个数
	public PageBean<TaskOfSch> queryTaskListState(int currentPage,String pid,String fId);//查看任务完成状态
	public String getAlias(String pId);//通过项目ID，查询项目的别名
	public List<AttrOfPro> findProAttrByPid(String aPid);//获取表头
	public PageBean<List<String>> getDataOfCol(String pId,String tableName,String sId,String cId,int currentPage);//得到数据
	public PageBean<List<String>> getAllData(String pId,String tableName,String sId,int currentPage);//得到自己项目下所有的数据
	
	public void affirm(String tId);//学校用户确认二级学院用户任务
	public void reject(ToolForGetCount tool);//修改状态，加入驳回理由
	public void modifyStateOfDtae(ToolForGetCount tool);//驳回后修改原数据的数据状态
	
	public int getStateNumOfNotConfirm(DispensePro dispensePro);//获取没有确认的数据的名单。
	public void modifyStateOftask(String id);//学校修改自己在教育厅列表中的任务进度状态
	public void modifydataStateTo3(ToolForGetCount tool);//学校修改数据表中，自己的数据的状态为3

}

