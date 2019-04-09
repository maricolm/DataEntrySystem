package com.pdata.col.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndktools.javamd5.Mademd5;
import com.pdata.col.mapper.ColMapper;
import com.pdata.col.pojo.Col;
import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.col.service.ColService;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.Pro;
import com.pdata.school.pojo.TaskOfSch;
import com.pdata.until.JDBCTools;
import com.pdata.until.PageBean;

/**
 * @author mulming
 * @time 2018年5月20日
 * @descript ColServiceImpl
 */
@Service
public class ColServiceImpl implements ColService {

	@Autowired
	private ColMapper colMapper;
	@Override
	public Col loginCol(Col col) {
		Mademd5 md=new Mademd5();
		col.setcPswd(md.toMd5(col.getcPswd()));
		return colMapper.loginCol(col);
	}
	
	@Override
	public void modifyInfo(Col col) {
		colMapper.modifyInfo(col);
	}
	
	@Override
	public Col findColByCid(String cId) {
		return colMapper.findColByCid(cId);
	}
	
	@Override
	public void modifypswd(Col col) {
		Mademd5 md=new Mademd5();
		col.setcPswd(md.toMd5(col.getcPswd()));
		colMapper.modifypswd(col);
	}

	@Override
	public int findSelfProCount(String cId) {
		return colMapper.findSelfProCount(cId);
	}

	@Override
	public PageBean<TaskOfSch> proList(int currentPage, String cId) {
		int count=findSelfProCount(cId);
		PageBean<TaskOfSch> pb=new PageBean<TaskOfSch>(currentPage, count, 10);
		pb.setCondition(cId);
		List<TaskOfSch> task=colMapper.proList(pb);
		pb.setBeanList(task);
		return pb;
	}

	@Override
	public void acceptTask(String tId) {
		colMapper.acceptTask(tId);
	}

	@Override
	public List<AttrOfPro> findProAttrByPid(String aPid) {
		return colMapper.findProAttrByPid(aPid);
	}

	@Override
	public PageBean<List<String>> getDataOfProByPid(String pId,String cId,int currentPage) {
		JDBCTools jdbc=new JDBCTools();
		int len=colMapper.getLenAttrOfProByPid(pId);
		//1、获取项目的别名
			String alias=colMapper.getAlias(pId);
			ToolForGetCount tool=new ToolForGetCount(alias,cId);
		//2、根据项目别名去查询该表中该用户的数据
			//2.1、准备sql
			int count=colMapper.getCountDate(tool);//计算当前的总数
			
			PageBean<List<String>> pb=new PageBean<List<String>>(currentPage, count, 10);
			//String sql="select * from "+alias+" where colId='"+cId+"' limit" +"'"+pb.getStartIndex()+","+pb.getPageSize();
			String sql="select * from "+alias+" where colId='"+cId+"' limit "+pb.getStartIndex()+","+pb.getPageSize();
			//System.out.println(sql);
			//2.2、获取数据
			List<List<String>> data=jdbc.findResultSet(sql, len);
			pb.setBeanList(data);
		return pb;
	}

	@Override
	public String getAlias(String pId) {
		return colMapper.getAlias(pId);
	}

	@Override
	public Pro getProByPid(String pid) {
		return colMapper.getProByPid(pid);
	}

	@Override
	public void deleteDataById(ToolForGetCount tool) {
		colMapper.deleteDataById(tool);
	}
	
	@Override
	public void callBackData(String table, String colid) {
		ToolForGetCount tool=new ToolForGetCount(table, colid);
		colMapper.callBackData(tool);
	}

	@Override
	public void updateDataState(String table, String colid) {
		ToolForGetCount tool=new ToolForGetCount(table, colid);
		colMapper.updateDataState(tool);
	}

	@Override
	public void updateProState(String tId) {
		colMapper.updateProState(tId);
	}

}
