package com.pdata.school.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ndktools.javamd5.Mademd5;
import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Tools;
import com.pdata.school.mapper.SchMapper;
import com.pdata.school.pojo.Sch;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.school.service.SchService;
import com.pdata.until.JDBCTools;
import com.pdata.until.PageBean;
import cn.itcast.commons.CommonUtils;

@Service
public class SchServiceImpl implements SchService {
	@Autowired
	private SchMapper schMapper;

	@Override
	public Sch schLogin(Sch sch) {
		Mademd5 md=new Mademd5();
		sch.setsPswd(md.toMd5(sch.getsPswd()));
		return schMapper.schLogin(sch);
	}

	@Override
	public void modifyInfo(Sch sch) {
		schMapper.modifyInfo(sch);
	}

	@Override
	public Sch findUserBySid(String sId) {
		return schMapper.findUserBySid(sId);
	}

	@Override
	public void modifypswd(Sch sch) {
		Mademd5 md=new Mademd5();
		sch.setsPswd(md.toMd5(sch.getsPswd()));
		schMapper.modifypswd(sch);
	}

	@Override
	public Boolean verifyAddAccount(String account) {
		return schMapper.verifyAddAccount(account);
	}

	@Override
	public void addCol(Col col) {
		//1,补全id
		String cId=CommonUtils.uuid();
		col.setcId(cId);//补全主键id
		Mademd5 md=new Mademd5();
		col.setcPswd(md.toMd5("000000"));
		col.setcRole(3);
		schMapper.addCol(col);
	}

	@Override
	public int findCountColUser(String condition) {
		return schMapper.findCountColUser(condition);
	}

	@Override
	public PageBean<Col> findUser(int currentPage,String condition) {
		int count=findCountColUser(condition);//查询用户总数
		PageBean<Col> pb=new PageBean<>(currentPage, count, 10);
		pb.setCondition(condition);
		List<Col> cols=schMapper.findUser(pb);
		pb.setBeanList(cols);
		return pb;
	}

	@Override
	public void deletdUserById(String cId) {
		schMapper.deletdUserById(cId);
	}

	@Override
	public void resetPswdForUser(Col col) {
		Mademd5 md=new Mademd5();
		col.setcPswd(md.toMd5("000000"));
		schMapper.resetPswdForUser(col);
	}

	@Override
	public int findCountBeCompleted(String sid) {
		return schMapper.findCountBeCompleted(sid);
	}
	
	@Override
	public PageBean<DispensePro> queryProOfBeCompleted(int currentPage,String sId) {
		int count=findCountBeCompleted(sId);//查询待接收项目的总数
		PageBean<DispensePro> pb=new PageBean<>(currentPage, count, 10);
		pb.setCondition(sId);
		List<DispensePro> proOfBeComleted=schMapper.queryProOfBeCompleted(pb);
		/*List<Pro> pro=new ArrayList<Pro>();
		for(int i=0;i<proOfBeComleted.size();i++) {
			pro.add(schMapper.queryProByPid(proOfBeComleted.get(i).getPid()));
		}*/
		pb.setBeanList(proOfBeComleted);
		return pb;
	}

	@Override
	public List<Col> findAllColList(String cCid) {
		return schMapper.findAllColList(cCid);
	}

	@Override
	public DispensePro findDisPenseProByDid(String did) {
		return schMapper.findDisPenseProByDid(did);
	}
	
	@Override
	public void updateCState(String dId) {
		schMapper.updateCState(dId);
	}
	
	@Override//参数为 学校的ID，教育厅任务列表的id
	public void insertProToTaskOfSch(String sId,String did) {
		//首先修改任务表中自己的接收状态
		updateCState(did);
		//把该任务存储到自己的任务表中
		List<Col> coll=findAllColList(sId);//查出属于自己的所有用户
		DispensePro dpro=findDisPenseProByDid(did);//查询出任务
		String pId=dpro.getPid();
		String pName=dpro.getpName();
		String eEname=dpro.geteEname();
		String pUrl=dpro.getpUrl();
		Date pDate=dpro.getpDate();
		for(int i=0;i<coll.size();i++) {
			TaskOfSch task=new TaskOfSch();
			task.settId(CommonUtils.uuid());
			task.setpId(pId);
			task.setfId(sId);
			task.setpName(pName);
			task.setcId(coll.get(i).getcId());
			task.setcName(coll.get(i).getcUname());
			task.settDepartment(coll.get(i).getcDepartment());
			task.settPState(1);
			task.settState(1);
			task.seteEname(eEname);
			task.setpUrl(pUrl);
			task.setpDate(pDate);
			schMapper.insertProToTaskOfSch(task);
		}
	}

	@Override
	public int queryCountColOfPro(String pId,String fId) {
		Tools t=new Tools();
		t.setAttr1(pId);
		t.setAttr2(fId);
		return schMapper.queryCountColOfPro(t);
	}

	@Override
	public PageBean<TaskOfSch> queryColOfPro(int currentPage,String pid,String fId) {
		int userCount=queryCountColOfPro(pid,fId);
		PageBean<TaskOfSch> pb=new PageBean<TaskOfSch>(currentPage, userCount, 10);
		pb.setCondition(pid);
		pb.setId(fId);
		List<TaskOfSch> taskOfSch=schMapper.queryColOfPro(pb);
		pb.setBeanList(taskOfSch);
		return pb;
	}

	@Override
	public void assignTaskToCol(String tid) {
		schMapper.assignTaskToCol(tid);
	}

	@Override
	public int queryCountColOfProState(String pId, String fId) {
		Tools t=new Tools();
		t.setAttr1(pId);
		t.setAttr2(fId);
		return schMapper.queryCountColOfProState(t);
	}

	@Override
	public PageBean<TaskOfSch> queryTaskListState(int currentPage, String pid, String fId) {
		int userCount=queryCountColOfProState(pid,fId);
		PageBean<TaskOfSch> pb=new PageBean<TaskOfSch>(currentPage, userCount, 10);
		pb.setCondition(pid);
		pb.setId(fId);
		List<TaskOfSch> taskOfSch=schMapper.queryTaskListState(pb);
		pb.setBeanList(taskOfSch);
		return pb;
	}

	@Override
	public String getAlias(String pId) {
		return schMapper.getAlias(pId);
	}
	@Override
	public List<AttrOfPro> findProAttrByPid(String aPid) {
		return schMapper.findProAttrByPid(aPid);
	}

	@Override
	public PageBean<List<String>> getDataOfCol(String pId,String tableName, String sId, String cId,int currentPage) {
		JDBCTools jdbc=new JDBCTools();
		//获取
		int len=schMapper.getLenAttrOfProByPid(pId);
		ToolForGetCount tool=new ToolForGetCount(tableName,sId,cId);
		//2、根据项目别名去查询该表中该用户的数据
			//2.1、准备sql
			int count=schMapper.getCountOfColDate(tool);//计算当前的总数
			
			PageBean<List<String>> pb=new PageBean<List<String>>(currentPage, count, 10);
			//String sql="select * from "+alias+" where colId='"+cId+"' limit" +"'"+pb.getStartIndex()+","+pb.getPageSize();
			String sql="select * from "+tableName+" where colId='"+cId+"' and schid='"+sId+"' limit "+pb.getStartIndex()+","+pb.getPageSize();
			//System.out.println(sql);
			//2.2、获取数据
			List<List<String>> data=jdbc.findResultSet(sql, len);
			pb.setBeanList(data);
		return pb;
	}

	@Override
	public PageBean<List<String>> getAllData(String pId, String tableName, String sId, int currentPage) {
		JDBCTools jdbc=new JDBCTools();
		//获取
		int len=schMapper.getLenAttrOfProByPid(pId);
		ToolForGetCount tool=new ToolForGetCount(tableName,sId);
		//2、根据项目别名去查询该表中该用户的数据
			//2.1、准备sql
			int count=schMapper.getAllDataCount(tool);//计算当前的总数
			
			PageBean<List<String>> pb=new PageBean<List<String>>(currentPage, count, 10);
			//String sql="select * from "+alias+" where colId='"+cId+"' limit" +"'"+pb.getStartIndex()+","+pb.getPageSize();
			String sql="select * from "+tableName+" where state!=1 and schid='"+sId+"' limit "+pb.getStartIndex()+","+pb.getPageSize();
			//System.out.println(sql);
			//2.2、获取数据
			List<List<String>> data=jdbc.findResultSet(sql, len);
			pb.setBeanList(data);
		return pb;
	}

	/* 
	 * mulming
	 */
	@Override
	public void affirm(String tId) {
		schMapper.affirm(tId);
	}

	@Override
	public void reject(ToolForGetCount tool) {
		schMapper.reject(tool);
	}

	@Override
	public void modifyStateOfDtae(ToolForGetCount tool) {
		schMapper.modifyStateOfDtae(tool);
	}

	@Override
	public int getStateNumOfNotConfirm(DispensePro dispensePro) {
		return schMapper.getStateNumOfNotConfirm(dispensePro);
	}

	@Override
	public void modifyStateOftask(String id) {
		schMapper.modifyStateOftask(id);
	}

	@Override
	public void modifydataStateTo3(ToolForGetCount tool) {
		schMapper.modifydataStateTo3(tool);
	}

}
